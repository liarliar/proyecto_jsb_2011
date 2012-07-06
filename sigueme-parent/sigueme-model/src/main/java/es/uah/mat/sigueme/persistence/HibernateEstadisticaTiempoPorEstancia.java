package es.uah.mat.sigueme.persistence;

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.orm.hibernate3.*;
import org.springframework.orm.hibernate3.support.*;
import org.springframework.stereotype.*;

import es.uah.mat.sigueme.estadistica.*;

@Repository("estadisticaTiempoPorEstancia")
public class HibernateEstadisticaTiempoPorEstancia extends HibernateDaoSupport implements
		EstadisticaPorEstancia {

	@Autowired
	public HibernateEstadisticaTiempoPorEstancia (SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ZonaTiempoVisita> getTiempoPorSala(final Date fechaInicio,
			final Date fechaFin, final TipoTiempoEstancia tipoTiempoEstancia) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaTiempoVisita>>() {

			@Override
			public List<ZonaTiempoVisita> doInHibernate(final Session session)
					throws HibernateException, SQLException {
				final List<ZonaTiempoVisita> zonaTiempoVisita = new ArrayList<ZonaTiempoVisita>();
				String dateClause ="";
				if (fechaInicio != null) {
					dateClause += "and fechaentrada >=  :fechaInicio " ;
				}
				if (fechaFin != null) {
					dateClause += "and fechaentrada <=  :fechaFin " ;
				}
				
						
				final SQLQuery query = session.createSQLQuery("SELECT zona.nombre,extract(epoch FROM ("+tipoTiempoEstancia.getSql()+"(fechasalidaid-fechaentrada)))" +
						" FROM recorrido_sala, zona where zona.id = salaid and fechasalidaid is not null " + dateClause+" group by zona.nombre");

				if (fechaInicio != null) {
					query.setDate("fechaInicio", fechaInicio);
				}
				if (fechaFin != null) {
					query.setDate("fechaFin", fechaFin);
				}
				List<Object[]> result = query.list();
				
				for (Object[] columnas : result) {
					zonaTiempoVisita.add(new ZonaTiempoVisita((String)columnas[0], new Long(((Double)columnas[1]).longValue())));
				}
				return zonaTiempoVisita;
			}

		});
	}

}

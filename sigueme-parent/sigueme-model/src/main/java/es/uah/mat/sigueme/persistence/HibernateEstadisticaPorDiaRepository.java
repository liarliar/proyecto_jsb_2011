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

@Repository("estadisticaPorDiaRepository")
public class HibernateEstadisticaPorDiaRepository extends HibernateDaoSupport implements
		EstadisticaPorDiaRepository {


	@Autowired
	public HibernateEstadisticaPorDiaRepository(
			SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ZonaVisitante> getVisitantesPorSala(final Date fecha) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitante>>() {

			@Override
			public List<ZonaVisitante> doInHibernate(final Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitante> zonasVisitadas = new ArrayList<ZonaVisitante>();
				final SQLQuery query = session.createSQLQuery("select count(a.\"idRecorrido\") visitantes, " +
						"d.nombre from recorrido_mensajerfid a, mensajerfid b, " +
						"puerta c, zona d where a.\"idMensaje\" = b.id and a.tipo = 'E' and " +
						"b.idpuerta = c.id and c.idzona2 = d.id and cast(b.fecha as date) = :fecha " +
						"group by d.nombre;");
				query.setDate("fecha", fecha);
				List<Object[]> result = query.list();
				
				for (Object[] columnas : result) {
					zonasVisitadas.add(new ZonaVisitante((String)columnas[1], new Long(((BigInteger)columnas[0]).longValue())));
				}
				return zonasVisitadas;
			}
		});
	}


}

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
	public List<ZonaVisitante> getVisitantesPorSala(final Date fecha, final Rango rangoHoras) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitante>>() {

			@Override
			public List<ZonaVisitante> doInHibernate(final Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitante> zonasVisitadas = new ArrayList<ZonaVisitante>();
				final SQLQuery query = session.createSQLQuery("select count(*) visitantes,nombre from (" +
						"select recorridoid,salaid from recorrido_sala " +
						"where cast(fechaentrada as date) = :fecha " + getSqlRangoFechas(rangoHoras) +
								" group by recorridoid,salaid) as visita,zona " +
						" where visita.salaid=id group by nombre order by nombre desc");
				
				setRangosFecha(rangoHoras, query);
				query.setDate("fecha", fecha);
				List<Object[]> result = query.list();
				
				for (Object[] columnas : result) {
					zonasVisitadas.add(new ZonaVisitante((String)columnas[1], new Long(((BigInteger)columnas[0]).longValue())));
				}
				return zonasVisitadas;
			}
		});
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ZonaVisitantePorHora> getVisitantesPorHoraEnCadaSala(final Date fecha, final Rango rangoHoras) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitantePorHora>>() {

			@Override
			public List<ZonaVisitantePorHora> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitantePorHora> visitantesPorHora = new ArrayList<ZonaVisitantePorHora>();
				final SQLQuery query = session.createSQLQuery("select count(*) visitantes,hora,nombre from (select recorridoid,salaid,extract(hour from fechaentrada) hora from recorrido_sala" +
						" where cast(fechaentrada as date) = :fecha "+ getSqlRangoFechas(rangoHoras) +
						" group by recorridoid,salaid,extract(hour from fechaentrada)) as visita,zona " +
						"where visita.salaid=id group by nombre,hora order by nombre, hora");

				setRangosFecha(rangoHoras, query);
				query.setDate("fecha", fecha);
				final List<Object[]> result = query.list();
				final Map<String, List<HoraVisitante>> horaVisitante = new HashMap<String, List<HoraVisitante>>();
				for (Object[] columnas : result) {
					String zona = (String) columnas[2];
					if (!horaVisitante.containsKey(zona)) {
						horaVisitante.put(zona, new ArrayList<HoraVisitante>());
					}
					horaVisitante.get(zona).add(new HoraVisitante(((Double)columnas[1]).longValue(), ((BigInteger)columnas[0]).longValue()));
				}
				
				for (String zona : horaVisitante.keySet()) {
					visitantesPorHora.add(new ZonaVisitantePorHora(zona, horaVisitante.get(zona)));
				}
				return visitantesPorHora;
			}


		});
	}
	private void setRangosFecha(final Rango rangoHoras,
			final SQLQuery query) {
		if (rangoHoras.getInicio() != null) {
			query.setInteger("horaInicio", rangoHoras.getInicio());
		}
		if (rangoHoras.getFin() != null) {
			query.setInteger("horaFin", rangoHoras.getFin());
		}
	}
	
	private String getSqlRangoFechas(Rango rangoHoras) {
		final StringBuffer filtro = new StringBuffer();
		
		if (rangoHoras.getInicio() != null) {
			filtro.append(" and extract(hour from fechaentrada) >= :horaInicio");
		}
		
		if (rangoHoras.getFin() != null) {
			filtro.append(" and extract(hour from fechaentrada) <= :horaFin");
		}
		return filtro.toString();
	}


}

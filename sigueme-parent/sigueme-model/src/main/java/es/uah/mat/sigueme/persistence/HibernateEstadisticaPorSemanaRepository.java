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

@Repository("estadisticaPorSemanaRepository")
public class HibernateEstadisticaPorSemanaRepository extends HibernateDaoSupport implements
		EstadisticaPorSemanaRepository {

	@Autowired
	public HibernateEstadisticaPorSemanaRepository(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ZonaVisitante> getVisitantesPorZona(final Date fecha) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitante>>() {

			@Override
			public List<ZonaVisitante> doInHibernate(final Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitante> zonasVisitadas = new ArrayList<ZonaVisitante>();
				final SQLQuery query = session.createSQLQuery("select count(*) visitantes,nombre from (select recorridoid,salaid from recorrido_sala " +
						" where cast(fechaentrada as date) >= :inicio and cast(fechaentrada as date) <= :fin " + 
						" group by recorridoid,salaid) as visita,zona where visita.salaid=id " +
						" group by nombre");
				query.setDate("inicio", getInicioSemana(fecha));
				query.setDate("fin", getFinSemana(fecha));
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
	public List<ZonaVisitantePorDiaSemana> getVisitantesPorDiaSemanaEnCadaSala(final Date fecha) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitantePorDiaSemana>>() {

			@Override
			public List<ZonaVisitantePorDiaSemana> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitantePorDiaSemana> visitantesPorDiaSemana = new ArrayList<ZonaVisitantePorDiaSemana>();
				final SQLQuery query = session.createSQLQuery("select count(*) visitantes,diaSemana,nombre from " +
						"(select recorridoid,salaid,extract(dow from fechaentrada) diaSemana from recorrido_sala " +
						" where cast(fechaentrada as date) >= :inicio and cast(fechaentrada as date) <= :fin " +
						" group by recorridoid,salaid,extract(dow from fechaentrada)) as visita,zona where visita.salaid=id " +
						" group by nombre,diaSemana");
				
				query.setDate("inicio", getInicioSemana(fecha));
				query.setDate("fin", getFinSemana(fecha));
				
				final List<Object[]> result = query.list();
				final Map<String, List<SemanaVisitante>> semanaVisitante = new HashMap<String, List<SemanaVisitante>>();
				for (Object[] columnas : result) {
					String zona = (String) columnas[2];
					if (!semanaVisitante.containsKey(zona)) {
						semanaVisitante.put(zona, new ArrayList<SemanaVisitante>());
					}
					int diaSemana = (((Double)columnas[1]).intValue() + 6) % 7;
					semanaVisitante.get(zona).add(new SemanaVisitante(DiaSemana.values()[diaSemana], ((BigInteger)columnas[0]).longValue()));
				}
				
				for (String zona : semanaVisitante.keySet()) {
					visitantesPorDiaSemana.add(new ZonaVisitantePorDiaSemana(zona, semanaVisitante.get(zona)));
				}
				return visitantesPorDiaSemana;
			}
		});
	}
	
	private Date getInicioSemana(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(fecha);
		
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		return calendar.getTime();
	}

	private Date getFinSemana(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(fecha);
		
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		return calendar.getTime();
	}
}

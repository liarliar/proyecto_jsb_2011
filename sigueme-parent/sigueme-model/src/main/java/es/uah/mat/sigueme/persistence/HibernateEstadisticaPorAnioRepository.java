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

@Repository("estadisticaPorAnioRepository")
public class HibernateEstadisticaPorAnioRepository extends HibernateDaoSupport implements
		EstadisticaPorAnioRepository {

	@Autowired
	public HibernateEstadisticaPorAnioRepository(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ZonaVisitante> getVisitantesPorZona(final int anio) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitante>>() {

			@Override
			public List<ZonaVisitante> doInHibernate(final Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitante> zonasVisitadas = new ArrayList<ZonaVisitante>();
				final SQLQuery query = session.createSQLQuery("select count(*) visitantes,nombre from ( " +
						"select recorridoid,salaid from recorrido_sala " +
								"where extract(year from fechaentrada) = :anio " +
										"group by recorridoid,salaid) as visita,zona where visita.salaid=id group by nombre order by nombre desc");
				query.setInteger("anio", anio);
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
	public List<ZonaVisitantePorAnio> getVisitantesPorAnioEnCadaSala(final int anio) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitantePorAnio>>() {

			@Override
			public List<ZonaVisitantePorAnio> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitantePorAnio> visitantesPorAnio = new ArrayList<ZonaVisitantePorAnio>();
				final SQLQuery query = session.createSQLQuery("select count(*) visitantes,mes,nombre from ( " +
						"select recorridoid,extract(month from fechaentrada) mes,salaid from recorrido_sala " +
						" where extract(year from fechaentrada) = :anio " +
						"group by recorridoid,salaid,extract(month from fechaentrada)) as visita,zona " +
						"where visita.salaid=id group by nombre,mes order by nombre,mes");
				
				query.setInteger("anio", anio);
				
				final List<Object[]> result = query.list();
				final Map<String, List<AnioVisitante>> anioVisitante = new HashMap<String, List<AnioVisitante>>();
				for (Object[] columnas : result) {
					String zona = (String) columnas[2];
					if (!anioVisitante.containsKey(zona)) {
						anioVisitante.put(zona, new ArrayList<AnioVisitante>());
					}
					
					final int enumMes = ((Double)columnas[1]).intValue() - 1;
					final Mes mes = Mes.values()[enumMes];
					anioVisitante.get(zona).add(new AnioVisitante(mes, ((BigInteger)columnas[0]).longValue()));
				}
				
				for (String zona : anioVisitante.keySet()) {
					visitantesPorAnio.add(new ZonaVisitantePorAnio(zona, anioVisitante.get(zona)));
				}
				return visitantesPorAnio;
			}
		});
	}
	
	private Date getInicioMes(Mes mes, int anio) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, mes.ordinal());
		calendar.set(Calendar.YEAR, anio);
		
		return calendar.getTime();
	}

	private Date getFinMes(Mes mes, int anio) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.MONTH, mes.ordinal());
		calendar.set(Calendar.YEAR, anio);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getLeastMaximum(Calendar.DAY_OF_MONTH));
		
		return calendar.getTime();
	}
}

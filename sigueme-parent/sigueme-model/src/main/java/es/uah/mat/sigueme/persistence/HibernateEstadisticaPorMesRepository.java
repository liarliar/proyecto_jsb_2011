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

@Repository("estadisticaPorMesRepository")
public class HibernateEstadisticaPorMesRepository extends HibernateDaoSupport implements
		EstadisticaPorMesRepository {

	@Autowired
	public HibernateEstadisticaPorMesRepository(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ZonaVisitante> getVisitantesPorZona(final Mes mes, final int anio) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitante>>() {

			@Override
			public List<ZonaVisitante> doInHibernate(final Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitante> zonasVisitadas = new ArrayList<ZonaVisitante>();
				final SQLQuery query = session.createSQLQuery("select count(a.\"idRecorrido\") visitantes, " +
						"d.nombre from recorrido_mensajerfid a, mensajerfid b, " +
						"puerta c, zona d where a.\"idMensaje\" = b.id and a.tipo = 'E' and " +
						"b.idpuerta = c.id and c.idzona2 = d.id and cast(b.fecha as date) >= :inicio and cast(b.fecha as date) <= :fin " +
						"group by d.nombre;");
				query.setDate("inicio", getInicioMes(mes, anio));
				query.setDate("fin", getFinMes(mes, anio));
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
	public List<ZonaVisitantePorDiaMes> getVisitantesPorDiaMesEnCadaSala(final Mes mes, final int anio) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitantePorDiaMes>>() {

			@Override
			public List<ZonaVisitantePorDiaMes> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitantePorDiaMes> visitantesPorDiaMes = new ArrayList<ZonaVisitantePorDiaMes>();
				final SQLQuery query = session.createSQLQuery("select count(a.\"idRecorrido\") visitantes, " +
						"extract(day from b.fecha) diaMes, d.nombre " +
						"from recorrido_mensajerfid a, mensajerfid b, puerta c, " +
						"zona d where a.\"idMensaje\" = b.id and a.tipo = 'E' and  " +
						"b.idpuerta = c.id and c.idzona2 = d.id " +
						"and cast(b.fecha as date) >= :inicio and cast(b.fecha as date) <= :fin group " +
						"by d.nombre, diaMes order by d.nombre, diaMes");
				
				query.setDate("inicio", getInicioMes(mes, anio));
				query.setDate("fin", getFinMes(mes, anio));
				
				final List<Object[]> result = query.list();
				final Map<String, List<MesVisitante>> mesVisitante = new HashMap<String, List<MesVisitante>>();
				for (Object[] columnas : result) {
					String zona = (String) columnas[2];
					if (!mesVisitante.containsKey(zona)) {
						mesVisitante.put(zona, new ArrayList<MesVisitante>());
					}
					
					mesVisitante.get(zona).add(new MesVisitante(((Double)columnas[1]).intValue(), ((BigInteger)columnas[0]).longValue()));
				}
				
				for (String zona : mesVisitante.keySet()) {
					visitantesPorDiaMes.add(new ZonaVisitantePorDiaMes(zona, mesVisitante.get(zona)));
				}
				return visitantesPorDiaMes;
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

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
	public List<ZonaVisitante> getVisitantesPorZona(final Mes mes, final int anio, final Rango rango) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitante>>() {

			@Override
			public List<ZonaVisitante> doInHibernate(final Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitante> zonasVisitadas = new ArrayList<ZonaVisitante>();
				final SQLQuery query = session.createSQLQuery("select count(*) visitantes,nombre from (select recorridoid,salaid from recorrido_sala " +
						" where cast(fechaentrada as date) >= :inicio and cast(fechaentrada as date) <= :fin " + getSqlRangoFechas(rango) +
						" group by recorridoid,salaid) as visita,zona where visita.salaid=id " +
						" group by nombre order by nombre desc" );
						
				query.setDate("inicio", getInicioMes(mes, anio));
				query.setDate("fin", getFinMes(mes, anio));
				setRangosFecha(rango, query);
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
	public List<ZonaVisitantePorDiaMes> getVisitantesPorDiaMesEnCadaSala(final Mes mes, final int anio, final Rango rango) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<ZonaVisitantePorDiaMes>>() {

			@Override
			public List<ZonaVisitantePorDiaMes> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final List<ZonaVisitantePorDiaMes> visitantesPorDiaMes = new ArrayList<ZonaVisitantePorDiaMes>();
				final SQLQuery query = session.createSQLQuery("select count(*) visitantes,diaMes,nombre from " +
						"(select recorridoid,salaid,extract(day from fechaentrada) diaMes from recorrido_sala " +
						" where cast(fechaentrada as date) >= :inicio and cast(fechaentrada as date) <= :fin " + getSqlRangoFechas(rango) +
						" group by recorridoid,salaid,extract(day from fechaentrada)) as visita,zona where visita.salaid=id " +
						" group by nombre,diaMes");
				
				query.setDate("inicio", getInicioMes(mes, anio));
				query.setDate("fin", getFinMes(mes, anio));
				setRangosFecha(rango, query);
				
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
	
	private void setRangosFecha(final Rango rangoDias,
			final SQLQuery query) {
		if (rangoDias.getInicio() != null) {
			query.setInteger("diaInicio", rangoDias.getInicio());
		}
		if (rangoDias.getFin() != null) {
			query.setInteger("diaFin", rangoDias.getFin());
		}
	}
	
	private String getSqlRangoFechas(Rango rangoDias) {
		final StringBuffer filtro = new StringBuffer();
		
		if (rangoDias.getInicio() != null) {
			filtro.append(" and extract(day from fechaentrada) >= :diaInicio");
		}
		
		if (rangoDias.getFin() != null) {
			filtro.append(" and extract(day from fechaentrada) <= :diaFin");
		}
		return filtro.toString();
	}
}

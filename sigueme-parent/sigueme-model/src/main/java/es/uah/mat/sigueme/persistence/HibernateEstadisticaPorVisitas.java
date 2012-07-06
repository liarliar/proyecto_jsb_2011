package es.uah.mat.sigueme.persistence;

import java.util.*;

import org.apache.commons.lang.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.orm.hibernate3.*;
import org.springframework.orm.hibernate3.support.*;
import org.springframework.stereotype.*;

import es.uah.mat.sigueme.commons.util.*;
import es.uah.mat.sigueme.estadistica.*;
import es.uah.mat.sigueme.listapaginada.*;

@Repository("estadisticaPorVisitas")
public class HibernateEstadisticaPorVisitas extends HibernateDaoSupport implements
		EstadisticaPorVisitas {

	private final ListaPaginaRepository<Ruta> listaPaginadaRepository;
	
	@Autowired
	public HibernateEstadisticaPorVisitas (SessionFactory sessionFactory,ListaPaginaRepository<Ruta> listaPaginadaRepository) {
		super.setSessionFactory(sessionFactory);
		this.listaPaginadaRepository = listaPaginadaRepository;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ruta> getRutas(final Date fechaInicio,
			final Date fechaFin) {
		

			return getHibernateTemplate().executeFind(
					new HibernateCallback<List<Ruta>>() {

						public List<Ruta> doInHibernate(final Session session) {
							String dateClause ="";
							Map<String, Object> parameters = new HashMap<String, Object>();
							
							if (fechaInicio != null) {
								dateClause += " where ruta.inicio >=  :fechaInicio " ;
								parameters.put("fechaInicio", fechaInicio);
							}
							if (fechaFin != null) {
								if (StringUtils.isNotEmpty(dateClause)) {
									dateClause+= " and ";
								} else {
									dateClause+= " where ";
								}
								dateClause += " ruta.fin <=  :fechaFin " ;
								parameters.put("fechaFin", fechaFin);
							}
							return new ListaPaginada<Ruta>(
									new HibernateListaPaginaProvedorDatos<Ruta>(
											listaPaginadaRepository,
											"from Ruta ruta " + dateClause,
											"select count(ruta) from Ruta ruta " + dateClause,
											parameters));
						}
					});
			}

	@SuppressWarnings("unchecked")
	@Override
	public List<RecorridoSala> getRecorrido(final Integer id) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback<List<RecorridoSala>>() {

					public List<RecorridoSala> doInHibernate(final Session session) {
						final Query query = session.createQuery("from RecorridoSala where recorridoId=:recorridoId");
						query.setParameter("recorridoId", id);
						return query.list();
					}
				});
		}

}

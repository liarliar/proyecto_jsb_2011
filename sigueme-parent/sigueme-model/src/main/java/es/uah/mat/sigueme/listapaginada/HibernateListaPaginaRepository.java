package es.uah.mat.sigueme.listapaginada;

import java.sql.*;
import java.util.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.orm.hibernate3.*;
import org.springframework.stereotype.*;

@Repository("listaPaginaRepository")
public class HibernateListaPaginaRepository<T> extends HibernateTemplate
		implements ListaPaginaRepository<T> {

	@Autowired
	public HibernateListaPaginaRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Tupla<List<T>, Long> ejecutarConsultaPaginada(final String hqlQuery,
			final String hqlCountQuery, final int primerResultado,
			final int numeroResultados, final Map<String, Object> parametros) {
		return execute(new HibernateCallback<Tupla<List<T>, Long>>() {

			@SuppressWarnings("unchecked")
			@Override
			public Tupla<List<T>, Long> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Query query = session.createQuery(hqlQuery);
				final Query countQuery = session.createQuery(hqlCountQuery);

				setParametros(query, parametros);
				setParametros(countQuery, parametros);
				setPaginacion(query, primerResultado, numeroResultados);

				final Tupla<List<T>, Long> tupla = ejecutarTamano(
						numeroResultados, countQuery);
				final List<T> lista = query.list();
				tupla.setLista(lista);

				if (tupla.getTamano() == null) {
					tupla.setTamano(Long.valueOf(lista.size()));
				}
				return tupla;
			}
		});
	}

	@Override
	public Tupla<List<T>, Long> ejecutarConsultaPaginada(
			final String nativeQuery, final String nativeCountQuery,
			final int primerResultado, final int numeroResultados,
			final Class<T> entityClass, final Map<String, Object> parametros) {
		return execute(new HibernateCallback<Tupla<List<T>, Long>>() {

			@SuppressWarnings("unchecked")
			@Override
			public Tupla<List<T>, Long> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final SQLQuery query = session.createSQLQuery(nativeQuery);
				final SQLQuery countQuery = session
						.createSQLQuery(nativeCountQuery);

				setParametros(query, parametros);
				setParametros(countQuery, parametros);
				setPaginacion(query, primerResultado, numeroResultados);

				final Tupla<List<T>, Long> pair = ejecutarTamano(
						numeroResultados, countQuery);

				query.addEntity(entityClass);

				final List<T> list = query.list();
				pair.setLista(list);

				if (pair.getTamano() == null) {
					pair.setTamano(Long.valueOf(list.size()));
				}
				return pair;
			}
		});
	}

	@SuppressWarnings("unchecked")
	private Tupla<List<T>, Long> ejecutarTamano(int numeroResultados,
			final Query countQuery) {
		final Tupla<List<T>, Long> tupla = new Tupla<List<T>, Long>();
		if (numeroResultados > 0) {
			final Long numeroFila = Long.valueOf(countQuery.list().get(0)
					.toString());
			tupla.setTamano(numeroFila);

			if (numeroFila.intValue() == 0) {
				final List<T> listaVacia = Collections.EMPTY_LIST;
				tupla.setLista(listaVacia);
				return tupla;
			}
		}
		return tupla;
	}

	private void setParametros(Query query, final Map<String, Object> parametros) {
		if (parametros != null) {
			final Iterator<String> it = parametros.keySet().iterator();
			while (it.hasNext()) {
				final String key = it.next();
				final Object val = parametros.get(key);
				query.setParameter(key, val);
			}
		}
	}

	private static void setPaginacion(final Query query, int primerResultado,
			int maximoResultado) {
		query.setFirstResult(primerResultado);
		if (maximoResultado > 0) {
			query.setMaxResults(maximoResultado);
		}
	}

}

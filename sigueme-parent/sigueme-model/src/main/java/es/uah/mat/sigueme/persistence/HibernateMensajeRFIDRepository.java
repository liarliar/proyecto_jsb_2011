package es.uah.mat.sigueme.persistence;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import es.uah.mat.sigueme.commons.util.*;
import es.uah.mat.sigueme.listapaginada.*;

@Repository("mensajeRFIDRepository")
public class HibernateMensajeRFIDRepository extends HibernateDaoSupport
		implements MensajeRFIDRepository {

	private final ListaPaginaRepository<MensajeRFID> listaPaginadaRepository;

	@Autowired
	public HibernateMensajeRFIDRepository(
			ListaPaginaRepository<MensajeRFID> listaPaginadaRepository,
			SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
		this.listaPaginadaRepository = listaPaginadaRepository;
	}

	@SuppressWarnings("unchecked")
	public List<MensajeRFID> getMensajesRFID() {
		return getHibernateTemplate().executeFind(
				new HibernateCallback<List<MensajeRFID>>() {

					public List<MensajeRFID> doInHibernate(final Session session) {
						return new ListaPaginada<MensajeRFID>(
								new HibernateListaPaginaProvedorDatos<MensajeRFID>(
										listaPaginadaRepository,
										"from MensajeRFID",
										"select count(mensaje) from MensajeRFID mensaje",
										new HashMap<String, Object>()));
					}
				});
	}
}

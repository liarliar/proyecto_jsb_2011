package es.uah.mat.sigueme.persistence;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("mensajeRFIDRepository")
public class HibernateMensajeRFIDRepository extends HibernateDaoSupport
		implements MensajeRFIDRepository {

	@Autowired
	public HibernateMensajeRFIDRepository(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public List<MensajeRFID> getMensajesRFID() {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<MensajeRFID>>() {

			public List<MensajeRFID> doInHibernate(final Session session) {
				return session.getNamedQuery("MensajeRFID.getMensajesRFID").list();
			}	
		});
	}

}

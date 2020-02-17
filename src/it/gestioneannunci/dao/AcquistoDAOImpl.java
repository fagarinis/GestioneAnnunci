package it.gestioneannunci.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import it.gestioneannunci.model.Acquisto;

@Component
public class AcquistoDAOImpl implements AcquistoDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Acquisto> list() {
		return em.createQuery("From Acquisto", Acquisto.class).getResultList();
	}

	@Override
	public Acquisto get(long id) {
		return em.find(Acquisto.class, id);
	}

	@Override
	public void update(Acquisto o) {
		o = em.merge(o);
	}

	@Override
	public void insert(Acquisto o) {
		em.persist(o);
	}

	@Override
	public void delete(Acquisto o) {
		em.remove(em.merge(o));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acquisto> findByExample(Acquisto o) {
		Session session = (Session) em.getDelegate();

		@SuppressWarnings("serial")
		PropertySelector ps = new PropertySelector() {
			@Override
			public boolean include(Object object, String propertyName, Type type) {
				if (object == null)
					return false;
				// String
				if (object instanceof String)
					return StringUtils.isNotBlank((String) object);
				// Number
				if (object instanceof Integer)
					return ((Integer) object) != null;
				return true;
			}
		};

		Criteria objectCriteria = session.createCriteria(Acquisto.class);
		Example objectExample = Example.create(o).setPropertySelector(ps);

		objectCriteria.add(objectExample);
		return objectCriteria.list();
	}

	@Override
	public List<Acquisto> findAllByUserId(Long id) {
		return em.createQuery("From Acquisto a where a.utente.id ='" + id + "'", Acquisto.class).getResultList();
	}

}

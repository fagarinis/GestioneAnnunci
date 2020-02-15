package it.gestioneannunci.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;

import it.gestioneannunci.model.Ruolo;

public class RuoloDAOImpl implements RuoloDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Ruolo> list() {
		return em.createQuery("From Ruolo", Ruolo.class).getResultList();
	}

	@Override
	public Ruolo get(long id) {
		return em.find(Ruolo.class, id);
	}

	@Override
	public void update(Ruolo o) {
		o = em.merge(o);
	}

	@Override
	public void insert(Ruolo o) {
		em.persist(o);
	}

	@Override
	public void delete(Ruolo o) {
		em.remove(em.merge(o));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ruolo> findByExample(Ruolo o) {
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

		Criteria objectCriteria = session.createCriteria(Ruolo.class);
		Example objectExample = Example.create(o).setPropertySelector(ps);

		objectCriteria.add(objectExample);
		return objectCriteria.list();
	}

	@Override
	public Set<Ruolo> findByListOfIds(List<String> idList) {
		if (idList == null || idList.size() == 0) {
			return null;
		}
		String cond = "";
		for (int i = 0; i < idList.size(); i++) {
			if (i == 0) {
				cond = " r.id =" + idList.get(i) + " ";
			} else {
				cond += " OR r.id = " + idList.get(i) + " ";
			}
		}
		// prima c'era join fetch r.utenti
		List<Ruolo> ruoliList = em.createQuery("from Ruolo r  where " + cond, Ruolo.class).getResultList();
		Set<Ruolo> result = new HashSet<>();
		result.addAll(ruoliList);
		return result;
	}

}

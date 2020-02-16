package it.gestioneannunci.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import it.gestioneannunci.model.Ruolo;
import it.gestioneannunci.model.Utente;
import it.gestioneannunci.model.enumeration.StatoUtente;

@Component
public class UtenteDAOImpl implements UtenteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Utente> list() {
		return entityManager.createQuery("from Utente", Utente.class).getResultList();
	}

	@Override
	public Utente get(long id) {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public void update(Utente o) {
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Utente o) {
		entityManager.persist(o);
	}

	@Override
	public void delete(Utente o) {
		entityManager.remove(entityManager.merge(o));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utente> findByExample(Utente o) {
		Session session = (Session) entityManager.getDelegate();

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
					return ((Integer) object) != 0;

				return true;
			}
		};

		Example utenteExample = Example.create(o).setPropertySelector(ps);
		Criteria criteria = session.createCriteria(Utente.class).add(utenteExample);
		return criteria.list();
	}

	@Override
	public Utente executeLogin(String username, String password) {
		Query query = entityManager.createQuery(
				"select u FROM Utente u join fetch u.ruoli where u.username = :usernameParam and u.password= :passwordParam and u.stato=:statoUtenteParam");
		query.setParameter("usernameParam", username);
		query.setParameter("passwordParam", password);
		query.setParameter("statoUtenteParam", StatoUtente.ATTIVO);

		return query.getResultList().isEmpty() ? null : (Utente) query.getSingleResult();
	}

	@Override
	public Utente getEager(long id) {
		try {
			return entityManager.createQuery("from Utente u left join fetch u.ruoli where u.id =" + id, Utente.class)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void updateUserWithRoles(Utente utenteModel, List<String> listaIdRuoli) {
		utenteModel = entityManager.merge(utenteModel);
		for (String idRuolo : listaIdRuoli) {
			utenteModel.addRuolo(new Ruolo(Long.parseLong(idRuolo)));
		}

	}

	@Override
	public Utente findByUsername(String username) {
		try {
			return entityManager.createQuery("from Utente u where u.username ='" + username + "'", Utente.class)
					.getSingleResult();

		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Utente> findByExampleEager(Utente example) {
		List<Utente> result = new ArrayList<>();
		if (example == null) {
			return result;
		}

		String query = "Select distinct u From Utente u left join fetch u.ruoli r where 1=1 ";
		if (example.getNome() != null && example.getNome() != "") {
			query += " and u.nome = '" + example.getNome() + "'";
		}
		if (example.getCognome() != null && example.getCognome() != "") {
			query += " and u.cognome = '" + example.getCognome() + "'";
		}
		if (example.getUsername() != null && example.getUsername() != "") {
			query += " and u.username = '" + example.getUsername() + "'";
		}
		if (example.getEmail() != null && example.getEmail() != "") {
			query += " and u.email = '" + example.getEmail() + "'";
		}
		if (example.getStato() != null) {
			query += " and u.stato = '" + example.getStato() + "'";
		}

		result = entityManager.createQuery(query, Utente.class).getResultList();
		return result;
	}

	@Override
	public List<Utente> listEager() {
		return entityManager.createQuery("Select distinct u From Utente u left join fetch u.ruoli r",
				Utente.class).getResultList();
	}

	@Override
	public Utente findByEmail(String email) {
		try {
			return entityManager.createQuery("from Utente u where u.email ='" + email + "'", Utente.class)
					.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

}

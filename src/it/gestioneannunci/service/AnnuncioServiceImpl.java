package it.gestioneannunci.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.gestioneannunci.dao.AnnuncioDAO;
import it.gestioneannunci.dao.CategoriaDAO;
import it.gestioneannunci.dao.UtenteDAO;
import it.gestioneannunci.model.Annuncio;
import it.gestioneannunci.model.Categoria;
import it.gestioneannunci.model.Utente;

@Service
public class AnnuncioServiceImpl implements AnnuncioService {

	@Autowired
	private AnnuncioDAO annuncioDAO;

	@Autowired
	private UtenteDAO utenteDAO;

	@Autowired
	private CategoriaDAO categoriaDAO;

	@Transactional(readOnly = true)
	@Override
	public List<Annuncio> listAll() {
		return annuncioDAO.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Annuncio caricaSingolo(Long id) {
		return annuncioDAO.get(id);
	}

	@Transactional
	@Override
	public void aggiorna(Annuncio o) {
		annuncioDAO.update(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Annuncio o) {
		if (o.getUtente() == null) {
			return;
		}
		if (o.getCategorie() == null || o.getCategorie().size() == 0) {
			return;
		}

		// setto l'utente persistent da un utente transient che contiene
		// almeno l'id
		Utente utentePersistant = utenteDAO.get(o.getUtente().getId());

		// setto l'utente persistant
		o.setUtente(utentePersistant);
		utentePersistant.getAnnunci().add(o);

		// creo un set di categorie persistant da un set di categorie
		// transient che contengono almeno l'id
		Set<Categoria> categoriePersistant = new HashSet<>();
		for (Categoria categoria : o.getCategorie()) {
			Categoria categoriaPersistant = categoriaDAO.get(categoria.getId());
			categoriaPersistant.getAnnunci().add(o);
			categoriePersistant.add(categoriaPersistant);
		}
		// setto le categorie persistant
		o.setCategorie(categoriePersistant);

		// inserisco infine l'annuncio e lo faccio diventare persistant con
		// i vari collegamenti
		o.setDataInserimento(new Date());
		annuncioDAO.insert(o);
	}

	@Transactional
	@Override
	public void rimuovi(Annuncio o) {
		annuncioDAO.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		return annuncioDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Annuncio> cercaTuttiDaUtenteId(Long id) {
		return annuncioDAO.findAllByUtenteId(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Annuncio caricaAnnuncioEager(Long id) {
		return annuncioDAO.getEager(id);
	}

	@Transactional
	@Override
	public void rimuoviSeAnnuncioAperto(Annuncio o) {
		if (o == null || o.getId() == null) {
			return;
		}

		if (annuncioDAO.get(o.getId()).isAperto()) {
			annuncioDAO.delete(o);
		}
	}

	@Transactional
	@Override
	public boolean aggiornaSeAperto(Annuncio o) {
		if (o == null || o.getId() == null || o.getUtente() == null) {
			return false;
		}

		if (!annuncioDAO.get(o.getId()).isAperto()) {
			return false;
		}

		if (o.getCategorie() == null || o.getCategorie().size() == 0) {
			return false;
		}

		// setto l'utente persistent da un utente transient che contiene
		// almeno l'id
		Utente utentePersistant = utenteDAO.get(o.getUtente().getId());

		// setto l'utente persistant
		o.setUtente(utentePersistant);

		// creo un set di categorie persistant da un set di categorie
		// transient che contengono almeno l'id
		Set<Categoria> categoriePersistant = new HashSet<>();
		for (Categoria categoria : o.getCategorie()) {
			Categoria categoriaPersistant = categoriaDAO.get(categoria.getId());
			categoriaPersistant.getAnnunci().add(o);
			categoriePersistant.add(categoriaPersistant);
		}
		// setto le categorie persistant
		o.setCategorie(categoriePersistant);

		// inserisco infine l'annuncio e lo faccio diventare persistant con
		// i vari collegamenti
		o.setDataInserimento(annuncioDAO.get(o.getId()).getDataInserimento());
		annuncioDAO.update(o);
		return true;
	}

}

package it.gestioneannunci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.gestioneannunci.dao.AnnuncioDAO;
import it.gestioneannunci.model.Annuncio;

@Service
public class AnnuncioServiceImpl implements AnnuncioService {

	@Autowired
	private AnnuncioDAO annuncioDAO;

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

}

package it.gestioneannunci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.gestioneannunci.dao.AcquistoDAO;
import it.gestioneannunci.model.Acquisto;

@Service
public class AcquistoServiceImpl implements AcquistoService {

	@Autowired
	private AcquistoDAO acquistoDAO;

	@Transactional(readOnly = true)
	@Override
	public List<Acquisto> listAll() {
		return acquistoDAO.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Acquisto caricaSingolo(Long id) {
		return acquistoDAO.get(id);
	}

	@Transactional
	@Override
	public void aggiorna(Acquisto o) {
		acquistoDAO.update(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Acquisto o) {
		acquistoDAO.insert(o);
	}

	@Transactional
	@Override
	public void rimuovi(Acquisto o) {
		acquistoDAO.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Acquisto> findByExample(Acquisto example) {
		return acquistoDAO.findByExample(example);
	}

}

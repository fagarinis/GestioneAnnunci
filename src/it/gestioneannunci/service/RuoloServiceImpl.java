package it.gestioneannunci.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.gestioneannunci.dao.RuoloDAO;
import it.gestioneannunci.model.Ruolo;

@Service
public class RuoloServiceImpl implements RuoloService {

	@Autowired
	private RuoloDAO ruoloDAO;

	@Transactional(readOnly = true)
	@Override
	public List<Ruolo> listAll() {
		return ruoloDAO.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Ruolo caricaSingolo(Long id) {
		return ruoloDAO.get(id);
	}

	@Transactional
	@Override
	public void aggiorna(Ruolo o) {
		ruoloDAO.update(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Ruolo o) {
		ruoloDAO.update(o);
	}

	@Transactional
	@Override
	public void rimuovi(Ruolo o) {
		ruoloDAO.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Ruolo> findByExample(Ruolo example) {
		return ruoloDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	@Override
	public Set<Ruolo> trovaDaListaId(List<String> listaId) {
		if(listaId == null) {
			return new HashSet<>();
		}
		
		return ruoloDAO.findByListOfIds(listaId);
	}

}

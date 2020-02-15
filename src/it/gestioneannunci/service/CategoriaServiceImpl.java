package it.gestioneannunci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.gestioneannunci.dao.CategoriaDAO;
import it.gestioneannunci.model.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDAO categoriaDAO;

	@Transactional(readOnly = true)
	@Override
	public List<Categoria> listAll() {
		return categoriaDAO.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Categoria caricaSingolo(Long id) {
		return categoriaDAO.get(id);
	}

	@Transactional
	@Override
	public void aggiorna(Categoria o) {
		categoriaDAO.update(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Categoria o) {
		categoriaDAO.insert(o);
	}

	@Transactional
	@Override
	public void rimuovi(Categoria o) {
		categoriaDAO.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Categoria> findByExample(Categoria example) {
		return categoriaDAO.findByExample(example);
	}

}

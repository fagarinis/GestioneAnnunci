package it.gestioneannunci.dao;

import java.util.List;

import it.gestioneannunci.model.Annuncio;

public interface AnnuncioDAO extends IBaseDAO<Annuncio> {

	public List<Annuncio> findAllByUtenteId(long id);

	public Annuncio getEager(Long id);
	
	public List<Annuncio> findAllByTextWithAndIdCategory(String title, List<String> idCategoryList);

}

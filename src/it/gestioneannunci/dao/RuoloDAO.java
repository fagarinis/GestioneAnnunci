package it.gestioneannunci.dao;

import java.util.List;
import java.util.Set;

import it.gestioneannunci.model.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {
	
	public Set<Ruolo> findByListOfIds(List<String> idList);

}

package it.gestioneannunci.dao;

import java.util.List;

import it.gestioneannunci.model.Acquisto;

public interface AcquistoDAO extends IBaseDAO<Acquisto> {

	public List<Acquisto> findAllByUserId(Long id);

}

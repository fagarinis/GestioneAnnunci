package it.gestioneannunci.service;

import java.util.List;

import it.gestioneannunci.model.Annuncio;

public interface AnnuncioService extends IBaseService<Annuncio> {
	
	public List<Annuncio> cercaTuttiDaUtenteId(Long id);

}

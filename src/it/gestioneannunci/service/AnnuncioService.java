package it.gestioneannunci.service;

import java.util.List;

import it.gestioneannunci.model.Annuncio;

public interface AnnuncioService extends IBaseService<Annuncio> {
	
	public List<Annuncio> cercaTuttiDaUtenteId(Long id);

	public Annuncio caricaAnnuncioEager(Long id);
	
	public void rimuoviSeAnnuncioAperto(Annuncio o);

	/**
	 * Aggiorna l'annuncio se al momento della transazione l'annuncio è aperto
	 * @return true se l'aggiornamento ha successo
	 */
	public boolean aggiornaSeAperto(Annuncio o);

}

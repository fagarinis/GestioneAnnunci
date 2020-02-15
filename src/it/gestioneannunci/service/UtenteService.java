package it.gestioneannunci.service;

import java.util.List;

import it.gestioneannunci.model.Utente;

public interface UtenteService extends IBaseService<Utente> {

	public Utente eseguiAccesso(String username, String password);

	public Utente caricaSingoloUtenteEager(Long id);

	public void aggiornaUtenteConRuoli(Utente utenteModel, List<String> listaIdRuoli);

	public void inserisciNuovoUtenteClassico(Utente utenteInstance);
	
	public boolean isUsernameDiponibile(String username);

}

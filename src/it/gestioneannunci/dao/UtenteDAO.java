package it.gestioneannunci.dao;

import java.util.List;

import it.gestioneannunci.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {

	public Utente executeLogin(String username, String password);

	public Utente getEager(long id);

	public void updateUserWithRoles(Utente utenteModel, List<String> listaIdRuoli);

}

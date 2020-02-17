package it.gestioneannunci.service;

import java.util.List;

import it.gestioneannunci.model.Acquisto;
import it.gestioneannunci.model.Utente;

public interface AcquistoService extends IBaseService<Acquisto> {

	public List<Acquisto> cercaTuttiDaUtenteId(Long id);

	public boolean compraSeAperto(Long idAnnuncio, Utente o);

}

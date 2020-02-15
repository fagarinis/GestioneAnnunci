package it.gestioneannunci.service;

import java.util.List;
import java.util.Set;

import it.gestioneannunci.model.Ruolo;

public interface RuoloService extends IBaseService<Ruolo> {

	public Set<Ruolo> trovaDaListaId(List<String> listaId);

}

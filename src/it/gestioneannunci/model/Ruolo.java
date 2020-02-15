package it.gestioneannunci.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import it.gestioneannunci.model.enumeration.CodiceRuolo;

@Entity
public class Ruolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descrizione;
	@Enumerated(EnumType.STRING)
	private CodiceRuolo codice;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ruoli")
	private Set<Utente> utenti = new HashSet<>();

	public Ruolo() {
	}

	public Ruolo(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public CodiceRuolo getCodice() {
		return codice;
	}

	public void setCodice(CodiceRuolo codice) {
		this.codice = codice;
	}

	public Set<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}

}

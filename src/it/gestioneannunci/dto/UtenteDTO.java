package it.gestioneannunci.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import it.gestioneannunci.model.Ruolo;
import it.gestioneannunci.model.Utente;
import it.gestioneannunci.model.enumeration.StatoUtente;

public class UtenteDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String confermaPassword;
	private String email;
	private Double creditoResiduo = 0D;
	private StatoUtente stato = StatoUtente.CREATO;

	private Set<Ruolo> ruoli = new HashSet<>();
	private List<String> idRuoli = new ArrayList<>();

	public UtenteDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfermaPassword() {
		return confermaPassword;
	}

	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getCreditoResiduo() {
		return creditoResiduo;
	}

	public void setCreditoResiduo(Double creditoResiduo) {
		this.creditoResiduo = creditoResiduo;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public void setStato(String stato) {
		try {
			this.setStato(StatoUtente.valueOf(stato));

		} catch (Exception e) {
			this.stato = null;
		}
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public List<String> getIdRuoli() {
		return idRuoli;
	}

	public void setIdRuoli(List<String> idRuoli) {
		this.idRuoli = idRuoli;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.nome)) {
			result.add("il campo Nome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.cognome)) {
			result.add("il campo Cognome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.username)) {
			result.add("il campo Username non può essere vuoto");
		}

		if (StringUtils.isBlank(this.password)) {
			result.add("il campo Password non può essere vuoto");
		} else if (!password.equals(confermaPassword)) {
			result.add("conferma password diverse");
		}

		if (StringUtils.isBlank(this.email)) {
			result.add("il campo Email non può essere vuoto");
		} else if (!this.email.matches(
				"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
			result.add("formato Email non valido");
		}

		return result;
	}

	/**
	 * @return builda un oggetto Utente (I ruoli e la data di registrazione vanno
	 *         assegnati successivamente)
	 */
	public static Utente buildModelFromDto(UtenteDTO utenteDTO) {
		Utente result = new Utente();
		result.setId(utenteDTO.getId());
		result.setNome(utenteDTO.getNome());
		result.setCognome(utenteDTO.getCognome());
		result.setUsername(utenteDTO.getUsername());
		result.setPassword(utenteDTO.getPassword());
		result.setEmail(utenteDTO.getEmail());
		result.setCreditoResiduo(utenteDTO.getCreditoResiduo());
		result.setStato(utenteDTO.getStato());

		return result;
	}

}

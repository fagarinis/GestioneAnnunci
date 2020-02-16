package it.gestioneannunci.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.gestioneannunci.model.enumeration.StatoUtente;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(unique = true)
	private String email;
	@Column(name = "creditoresiduo")
	private Double creditoResiduo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datacreazione")
	private Date dataCreazione;
	@Enumerated(EnumType.STRING)
	private StatoUtente stato = StatoUtente.CREATO;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>();

	@OneToMany(mappedBy = "utente", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Annuncio> annunci = new HashSet<>();

	@OneToMany(mappedBy = "utente", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Acquisto> acquisti = new HashSet<>();

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

	public Date getDataCreazione() {
		return dataCreazione;
	}
	
	public Date getDataCreazioneSimpleFormat() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = this.dataCreazione;
		Date dateWithZeroTime = null;
		try {
			dateWithZeroTime = formatter.parse(formatter.format(date));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateWithZeroTime;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
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

	public Set<Annuncio> getAnnunci() {
		return annunci;
	}

	public void setAnnunci(Set<Annuncio> annunci) {
		this.annunci = annunci;
	}

	public Set<Acquisto> getAcquisti() {
		return acquisti;
	}

	public void setAcquisti(Set<Acquisto> acquisti) {
		this.acquisti = acquisti;
	}

	public void addRuolo(Ruolo ruolo) {
		this.ruoli.add(ruolo);
		ruolo.getUtenti().add(this);
	}

	public void addAnnuncio(Annuncio annuncio) {
		this.annunci.add(annuncio);
		annuncio.setUtente(this);
	}

	public void addAcquisto(Acquisto acquisto) {
		this.acquisti.add(acquisto);
		acquisto.setUtente(this);
	}

	public boolean isAdmin() {
		for (Ruolo ruolo : ruoli) {
			if (ruolo.getId() == Ruolo.ADMIN_ID)
				return true;
		}
		return false;
	}

}

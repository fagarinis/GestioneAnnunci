package it.gestioneannunci.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import it.gestioneannunci.model.Annuncio;
import it.gestioneannunci.model.Categoria;
import it.gestioneannunci.model.Utente;
import it.gestioneannunci.model.utils.ValutaUtils;

public class AnnuncioDTO {

	private Long id;
	private boolean aperto = true;
	private String testoAnnuncio;
	private Double prezzo;
	private Date dataInserimento;
	private Utente utente;
	private Set<Categoria> categorie = new HashSet<>();

	private List<String> listaIdCategorie = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAperto() {
		return aperto;
	}

	public void setAperto(boolean aperto) {
		this.aperto = aperto;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		try {
			this.prezzo = ValutaUtils.round(prezzo, 2);
		} catch (Exception e) {
		}
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}

	public List<String> getListaIdCategorie() {
		return listaIdCategorie;
	}

	public void setListaIdCategorie(List<String> listaIdCategorie) {
		this.listaIdCategorie = listaIdCategorie;
	}

	public void setPrezzo(String prezzo) {
		try {
			this.setPrezzo(Double.parseDouble(prezzo));
		} catch (Exception e) {
			this.prezzo = null;
		}
	}

	public void setCategorie(String[] idCategorie) {
		if (idCategorie == null) {
			return;
		}

		for (String idCategoria : idCategorie) {
			this.categorie.add(new Categoria(Long.parseLong(idCategoria)));
		}

	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.testoAnnuncio)) {
			result.add("il campo Testo Annuncio non può essere vuoto");
		}
		if (this.getPrezzo() == null || this.getPrezzo() < 0) {
			result.add("il campo Prezzo non è valido");
		}
		if (this.getCategorie() == null || this.getCategorie().size() == 0) {
			result.add("almeno una categoria deve essere selezionata");
		}

		return result;
	}

	public static Annuncio buildModelFromDto(AnnuncioDTO annuncioDTO) {
		Annuncio result = new Annuncio();
		result.setId(annuncioDTO.getId());
		result.setTestoAnnuncio(annuncioDTO.getTestoAnnuncio());
		result.setAperto(annuncioDTO.isAperto());
		result.setPrezzo(annuncioDTO.getPrezzo());
		result.setUtente(annuncioDTO.getUtente());
		result.setCategorie(annuncioDTO.getCategorie());

		return result;
	}

}

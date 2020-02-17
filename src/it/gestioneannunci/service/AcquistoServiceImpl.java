package it.gestioneannunci.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.gestioneannunci.dao.AcquistoDAO;
import it.gestioneannunci.dao.AnnuncioDAO;
import it.gestioneannunci.dao.UtenteDAO;
import it.gestioneannunci.model.Acquisto;
import it.gestioneannunci.model.Annuncio;
import it.gestioneannunci.model.Utente;

@Service
public class AcquistoServiceImpl implements AcquistoService {

	@Autowired
	private AnnuncioDAO annuncioDAO;
	
	@Autowired
	private AcquistoDAO acquistoDAO;
	
	@Autowired
	private UtenteDAO utenteDAO;

	@Transactional(readOnly = true)
	@Override
	public List<Acquisto> listAll() {
		return acquistoDAO.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Acquisto caricaSingolo(Long id) {
		return acquistoDAO.get(id);
	}

	@Transactional
	@Override
	public void aggiorna(Acquisto o) {
		acquistoDAO.update(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Acquisto o) {
		acquistoDAO.insert(o);
	}

	@Transactional
	@Override
	public void rimuovi(Acquisto o) {
		acquistoDAO.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Acquisto> findByExample(Acquisto example) {
		return acquistoDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Acquisto> cercaTuttiDaUtenteId(Long id) {
		return acquistoDAO.findAllByUserId(id);
	}

	/**
	 * L'utente 'o' compra l'annuncio con id 'idAnnuncio' se è aperto e viene inserito nella tabella acquisto
	 */
	@Transactional
	@Override
	public boolean compraSeAperto(Long idAnnuncio, Utente o) {
		if(idAnnuncio == null || o == null || o.getId() == null) {
			return false;
		}
		
		Annuncio annuncioPersist = annuncioDAO.get(idAnnuncio);
		Utente utentePersist = utenteDAO.get(o.getId());
		Double prezzo = annuncioPersist.getPrezzo();
		
		
		
		//verifico se l'annuncio è ancora aperto e se l'utente ha credito residuo
		if(!annuncioPersist.isAperto() || utentePersist.getCreditoResiduo()-prezzo < 0) {
			return false;
		}
		
		annuncioPersist.setAperto(false); //chiudo l'annuncio
		
		
		Acquisto acquistoResult = new Acquisto();
		acquistoResult.setDescrizione(annuncioPersist.getTestoAnnuncio());
		acquistoResult.setPrezzo(annuncioPersist.getPrezzo());
		acquistoResult.setAnno(Calendar.getInstance().get(Calendar.YEAR));
		//binding utente
		utentePersist.getAcquisti().add(acquistoResult);
		acquistoResult.setUtente(utentePersist);
		
		utentePersist.setCreditoResiduo(utentePersist.getCreditoResiduo()-prezzo);
		annuncioPersist.getUtente().setCreditoResiduo(annuncioPersist.getUtente().getCreditoResiduo()+prezzo);
		acquistoDAO.insert(acquistoResult); //inserisco l'acquisto
		return true;
	}

}

package it.gestioneannunci.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.gestioneannunci.dao.RuoloDAO;
import it.gestioneannunci.dao.UtenteDAO;
import it.gestioneannunci.model.Ruolo;
import it.gestioneannunci.model.Utente;
import it.gestioneannunci.model.enumeration.StatoUtente;

@Service
public class UtenteServiceImpl implements UtenteService {

	private static final Long idUtenteClassico = 2L;

	@Autowired
	private UtenteDAO utenteDAO;

	@Autowired
	private RuoloDAO ruoloDAO;

	@Transactional(readOnly = true)
	@Override
	public List<Utente> listAll() {
		return utenteDAO.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Utente caricaSingolo(Long id) {
		return utenteDAO.get(id);
	}

	@Transactional
	@Override
	public void aggiorna(Utente o) {
		utenteDAO.update(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Utente o) {
		o.setDataCreazione(new Date());
		utenteDAO.insert(o);
	}

	@Transactional
	@Override
	public void rimuovi(Utente o) {
		utenteDAO.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Utente> findByExample(Utente example) {
		return utenteDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	@Override
	public Utente eseguiAccesso(String username, String password) {
		return utenteDAO.executeLogin(username, password);
	}

	@Transactional(readOnly = true)
	@Override
	public Utente caricaSingoloUtenteEager(Long id) {
		return utenteDAO.getEager(id);
	}

	@Transactional
	@Override
	public void aggiornaUtenteConRuoli(Utente utenteModel, List<String> listaIdRuoli) {
		Utente utenteUpdate = utenteDAO.get(utenteModel.getId());
		utenteUpdate.setNome(utenteModel.getNome());
		utenteUpdate.setCognome(utenteModel.getCognome());
		utenteUpdate.setUsername(utenteModel.getUsername());
		utenteUpdate.setPassword(utenteModel.getPassword());
		utenteUpdate.setEmail(utenteModel.getEmail());
		utenteUpdate.setCreditoResiduo(utenteModel.getCreditoResiduo());
		utenteUpdate.setStato(utenteModel.getStato());

		utenteUpdate.getRuoli().clear();
		for (String idRuolo : listaIdRuoli) {
			Ruolo ruoloDaAggiungere = ruoloDAO.get(Long.parseLong(idRuolo));
			if (ruoloDaAggiungere != null)
				utenteUpdate.addRuolo(ruoloDaAggiungere);
		}
		utenteDAO.update(utenteUpdate);
	}

	@Transactional
	@Override
	public void inserisciNuovoUtenteConRuoli(Utente utenteInstance, List<String> listaIdRuoli) {
		utenteInstance.setStato(StatoUtente.ATTIVO);
		utenteInstance.setDataCreazione(new Date());
		utenteDAO.insert(utenteInstance);
		for (String idRuolo : listaIdRuoli) {
			Ruolo ruoloDaAggiungere = ruoloDAO.get(Long.parseLong(idRuolo));
			if (ruoloDaAggiungere != null)
				utenteInstance.addRuolo(ruoloDaAggiungere);
		}
	}

	@Transactional
	@Override
	public void inserisciNuovoUtenteClassico(Utente utenteInstance) {
		utenteInstance.setStato(StatoUtente.ATTIVO);
		utenteInstance.setDataCreazione(new Date());
		utenteDAO.insert(utenteInstance);
		utenteInstance.addRuolo(new Ruolo(idUtenteClassico));
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isUsernameDiponibile(String username) {
		return utenteDAO.findByUsername(username) == null;
	}

	@Transactional(readOnly = true)
	@Override
	public Utente cercaDaUsername(String username) {
		return utenteDAO.findByUsername(username);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Utente> listAllEager() {
		return utenteDAO.listEager();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Utente> findByExampleEager(Utente example) {
		return utenteDAO.findByExampleEager(example);
	}

}

package it.gestioneannunci.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.gestioneannunci.model.Annuncio;
import it.gestioneannunci.model.Utente;
import it.gestioneannunci.service.AcquistoService;
import it.gestioneannunci.service.AnnuncioService;
import it.gestioneannunci.service.UtenteService;

/**
 * Servlet implementation class ExecuteCompraAnnuncioServlet
 */
@WebServlet("/ExecuteCompraAnnuncioServlet")
public class ExecuteCompraAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;
	@Autowired
	private AnnuncioService annuncioService;
	@Autowired
	private AcquistoService acquistoService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteCompraAnnuncioServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long idAnnuncio = Long.parseLong(request.getParameter("idAnnuncio"));

		// prendo l'utente in sessione
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Utente utenteInSessione = (Utente) httpRequest.getSession().getAttribute("userInfo");
		
		boolean acquistoEffettuato = acquistoService.compraSeAperto(idAnnuncio, utenteInSessione);
		
		if(acquistoEffettuato) {
			request.setAttribute("listaAcquistiAttr", acquistoService.cercaTuttiDaUtenteId(utenteInSessione.getId()));
			//aggiorno l'utente in sessione
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", utenteService.caricaSingoloUtenteEager(utenteInSessione.getId()));
			request.getRequestDispatcher("/areariservata/storicoacquisti.jsp").forward(request, response);
		
		}
		else {
			Annuncio annuncio = annuncioService.caricaAnnuncioEager(idAnnuncio);
			request.setAttribute("usernameAttr", utenteService.cercaUsernameDaAnnuncio(annuncio));
			request.setAttribute("annuncioAttr", annuncio);
			request.setAttribute("messaggioErrore", "Errore di transazione");
			request.getRequestDispatcher("/acquisto.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	}

}

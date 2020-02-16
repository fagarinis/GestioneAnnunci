package it.gestioneannunci.web.servlet.areariservata;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.gestioneannunci.model.Annuncio;
import it.gestioneannunci.model.Utente;
import it.gestioneannunci.service.AnnuncioService;

/**
 * Servlet implementation class ExecuteEliminaAnnuncioServlet
 */
@WebServlet("/areariservata/imieiannunci/ExecuteEliminaAnnuncioServlet")
public class ExecuteEliminaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AnnuncioService annuncioService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteEliminaAnnuncioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long idAnnuncioDaEliminare = Long.parseLong(request.getParameter("idAnnuncio"));
		annuncioService.rimuoviSeAnnuncioAperto(new Annuncio(idAnnuncioDaEliminare));

		// prendo l'utente in sessione
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Utente utenteInSessione = (Utente) httpRequest.getSession().getAttribute("userInfo");

		// vado in pagina con OK
		request.setAttribute("listaAnnunciAttr", annuncioService.cercaTuttiDaUtenteId(utenteInSessione.getId()));
		request.setAttribute("messaggioConferma", "L'annuncio è stato eliminato");
		request.getRequestDispatcher("/areariservata/imieiannunci/IMieiAnnunci.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

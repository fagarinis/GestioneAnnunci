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
import it.gestioneannunci.service.CategoriaService;

/**
 * Servlet implementation class PrepareModificaAnnuncioServlet
 */
@WebServlet("/areariservata/imieiannunci/PrepareModificaAnnuncioServlet")
public class PrepareModificaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AnnuncioService annuncioService;

	@Autowired
	private CategoriaService categoriaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareModificaAnnuncioServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long idAnnuncioDaModificare = Long.parseLong(request.getParameter("idAnnuncio"));
		Annuncio AnnuncioDaModificare = annuncioService.caricaAnnuncioEager(idAnnuncioDaModificare);
		if (AnnuncioDaModificare.isAperto()) {
			request.setAttribute("annuncioAttr", AnnuncioDaModificare);
			request.setAttribute("categorieListAttr", categoriaService.listAll());
			request.getRequestDispatcher("/areariservata/imieiannunci/modifica.jsp").forward(request, response);
		} else {
			// prendo l'utente in sessione
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			Utente utenteInSessione = (Utente) httpRequest.getSession().getAttribute("userInfo");

			// vado in pagina con OK
			request.setAttribute("listaAnnunciAttr", annuncioService.cercaTuttiDaUtenteId(utenteInSessione.getId()));
			request.setAttribute("messaggioErrore", "Non è possibile modificare un annuncio chiuso");
			request.getRequestDispatcher("/areariservata/imieiannunci/IMieiAnnunci.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

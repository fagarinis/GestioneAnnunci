package it.gestioneannunci.web.servlet.areariservata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.gestioneannunci.dto.AnnuncioDTO;
import it.gestioneannunci.dto.UtenteDTO;
import it.gestioneannunci.model.Annuncio;
import it.gestioneannunci.model.Utente;
import it.gestioneannunci.service.AnnuncioService;
import it.gestioneannunci.service.CategoriaService;

/**
 * Servlet implementation class ExecuteInsertAnnuncioServlet
 */
@WebServlet("/areariservata/imieiannunci/ExecuteInsertAnnuncioServlet")
public class ExecuteInsertAnnuncioServlet extends HttpServlet {
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
	public ExecuteInsertAnnuncioServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// binding
		String testoAnnuncioInput = request.getParameter("testoAnnuncioInput");
		String prezzoInput = request.getParameter("prezzoInput");
		String[] idCategorieInputChecked = request.getParameterValues("categoriaInput");

		// prendo l'utente in sessione
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Utente utenteInSessione = (Utente) httpRequest.getSession().getAttribute("userInfo");

		AnnuncioDTO annuncioDTO = new AnnuncioDTO();
		annuncioDTO.setTestoAnnuncio(testoAnnuncioInput);
		annuncioDTO.setPrezzo(prezzoInput);
		annuncioDTO.setUtente(utenteInSessione);
		annuncioDTO.setAperto(true);
		annuncioDTO.setCategorie(idCategorieInputChecked);

		// verifica se ci sono errori, in caso ritorna indietro
		List<String> annuncioErrors = annuncioDTO.errors();

		if (!annuncioErrors.isEmpty()) {
			request.setAttribute("annuncioAttr", annuncioDTO);
			request.setAttribute("annuncioErrors", annuncioErrors);
			request.setAttribute("categorieListAttr", categoriaService.listAll());
			request.getRequestDispatcher("/areariservata/imieiannunci/insert.jsp").forward(request, response);
			return;
		}

		// inserisco nel DB
		Annuncio annuncioInstance = AnnuncioDTO.buildModelFromDto(annuncioDTO);
		annuncioService.inserisciNuovo(annuncioInstance);

		// vado in pagina con OK
		request.setAttribute("listaAnnunciAttr", annuncioService.cercaTuttiDaUtenteId(utenteInSessione.getId()));
		request.setAttribute("messaggioConfermaInserimento", "Il tuo annuncio è stato inserito correttamente");
		request.getRequestDispatcher("/areariservata/imieiannunci/IMieiAnnunci.jsp").forward(request, response);
	}

}

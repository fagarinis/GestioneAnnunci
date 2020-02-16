package it.gestioneannunci.web.servlet.areariservata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.gestioneannunci.dto.UtenteDTO;
import it.gestioneannunci.model.Utente;
import it.gestioneannunci.model.enumeration.StatoUtente;
import it.gestioneannunci.service.RuoloService;
import it.gestioneannunci.service.UtenteService;

/**
 * Servlet implementation class ExecuteModificaDaAreaRiservataUtenteServlet
 */
@WebServlet("/areariservata/ExecuteModificaDaAreaRiservataUtenteServlet")
public class ExecuteModificaDaAreaRiservataUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteModificaDaAreaRiservataUtenteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Utente utenteInSession = (Utente) httpRequest.getSession().getAttribute("userInfo");
		
		// binding
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String passwordInput = request.getParameter("passwordInput");

		// validazione
		UtenteDTO utenteDTO = new UtenteDTO();
		utenteDTO.setId(utenteInSession.getId());
		utenteDTO.setNome(nomeInput);
		utenteDTO.setCognome(cognomeInput);
		utenteDTO.setUsername(utenteInSession.getUsername());
		utenteDTO.setCreditoResiduo(utenteInSession.getCreditoResiduo());
		utenteDTO.setStato(utenteInSession.getStato());
		utenteDTO.setEmail(utenteInSession.getEmail());
		utenteDTO.setPassword(passwordInput);
		utenteDTO.setConfermaPassword(passwordInput);

		// verifica se ci sono errori, in caso ritorna indietro
		List<String> utenteErrors = utenteDTO.errors();
		if (!utenteErrors.isEmpty()) {
			request.setAttribute("utenteAttr", utenteDTO);
			request.setAttribute("utenteErrors", utenteErrors);
			request.getRequestDispatcher("/areariservata/modifica.jsp").forward(request, response);
			return;
		}

		// inserisco nel DB
		Utente utenteUpdate = UtenteDTO.buildModelFromDto(utenteDTO);
		utenteUpdate.setRuoli(utenteInSession.getRuoli());
		utenteUpdate.setDataCreazione(utenteInSession.getDataCreazione());
		utenteService.aggiorna(utenteUpdate);

		//aggiorno l'utente in sessione
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", utenteUpdate);
				
		// vado in pagina con OK
		request.setAttribute("messaggioConferma", "Modifica avvenuta con successo");
		request.getRequestDispatcher("/areariservata/home.jsp").forward(request, response);
	}

}

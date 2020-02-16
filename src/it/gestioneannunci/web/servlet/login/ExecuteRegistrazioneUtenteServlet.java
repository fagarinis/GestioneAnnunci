package it.gestioneannunci.web.servlet.login;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.gestioneannunci.dto.UtenteDTO;
import it.gestioneannunci.model.Utente;
import it.gestioneannunci.service.RuoloService;
import it.gestioneannunci.service.UtenteService;

/**
 * Servlet implementation class ExecuteRegistrazioneUtenteServlet
 */
@WebServlet("/ExecuteRegistrazioneUtenteServlet")
public class ExecuteRegistrazioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RuoloService ruoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteRegistrazioneUtenteServlet() {
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
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String usernameInput = request.getParameter("usernameInput");
		String passwordInput = request.getParameter("passwordInput");
		String confermaPasswordInput = request.getParameter("confermaPasswordInput");
		String emailInput = request.getParameter("emailInput");

		UtenteDTO utenteDTO = new UtenteDTO();
		utenteDTO.setNome(nomeInput);
		utenteDTO.setCognome(cognomeInput);
		utenteDTO.setUsername(usernameInput);
		utenteDTO.setPassword(passwordInput);
		utenteDTO.setConfermaPassword(confermaPasswordInput);
		utenteDTO.setEmail(emailInput);

		// verifica se ci sono errori, in caso ritorna indietro
		List<String> utenteErrors = utenteDTO.errors();
		if(!utenteService.isUsernameDiponibile(utenteDTO.getUsername())) {
			utenteErrors.add("username non disponibile");
		}
		
		if (utenteService.cercaDaEmail(utenteDTO.getEmail()) != null) {
			utenteErrors.add("L'email inserita è già in uso da un altro account");
		}
		
		if (!utenteErrors.isEmpty()) {
			request.setAttribute("utenteAttr", utenteDTO);
			request.setAttribute("utenteErrors", utenteErrors);
			request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
			return;
		}

		// inserisco nel DB
		Utente utenteInstance = UtenteDTO.buildModelFromDto(utenteDTO);
		utenteService.inserisciNuovoUtenteClassico(utenteInstance);

		// vado in pagina con OK
		request.setAttribute("messaggioConfermaRegistrazione", "Registrazione completata, ora puoi effettuare l'accesso");
		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}
}

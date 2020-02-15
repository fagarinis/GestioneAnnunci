package it.gestioneannunci.web.servlet.utente;

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
import it.gestioneannunci.model.enumeration.StatoUtente;
import it.gestioneannunci.service.RuoloService;
import it.gestioneannunci.service.UtenteService;

/**
 * Servlet implementation class ExecuteModificaUtenteServlet
 */
@WebServlet("/admin/gestioneutenti/ExecuteModificaUtenteServlet")
public class ExecuteModificaUtenteServlet extends HttpServlet {
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
	public ExecuteModificaUtenteServlet() {
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
		String idInput = request.getParameter("idInput");
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String emailInput = request.getParameter("emailInput");
		String usernameInput = request.getParameter("usernameInput");
		String passwordInput = request.getParameter("passwordInput");
		String creditoResiduoInput = request.getParameter("creditoResiduoInput");
		String[] idRuoliInputChecked = request.getParameterValues("ruoloInput");
		String statoInput = request.getParameter("statoInput");

		// validazione
		UtenteDTO utenteDTO = new UtenteDTO();
		utenteDTO.setId(Long.parseLong(idInput));
		utenteDTO.setNome(nomeInput);
		utenteDTO.setCognome(cognomeInput);
		utenteDTO.setEmail(emailInput);
		utenteDTO.setUsername(usernameInput);
		utenteDTO.setPassword(passwordInput);
		utenteDTO.setConfermaPassword(passwordInput);
		utenteDTO.setIdRuoli(idRuoliInputChecked);
		utenteDTO.setStato(statoInput);
		utenteDTO.setCreditoResiduo(creditoResiduoInput);

		// verifica se ci sono errori, in caso ritorna indietro
		List<String> utenteErrors = utenteDTO.errors();
		Utente utenteConStessoUsername = utenteService.cercaDaUsername(utenteDTO.getUsername());
		if (utenteConStessoUsername != null && utenteConStessoUsername.getId() != utenteDTO.getId()) {
			utenteErrors.add("username non disponibile");
		}
		if (!utenteErrors.isEmpty()) {
			utenteDTO.setRuoli(ruoloService.trovaDaListaId(utenteDTO.getIdRuoli()));
			request.setAttribute("utenteAttr", utenteDTO);
			request.setAttribute("utenteErrors", utenteErrors);
			request.setAttribute("listaRuoliCheckedAttr", utenteDTO.getIdRuoli());
			request.setAttribute("statiListAttr", StatoUtente.values());
			request.setAttribute("ruoliListAttr", ruoloService.listAll());

			request.getRequestDispatcher("/admin/gestioneutenti/modifica.jsp").forward(request, response);
			return;
		}

		// inserisco nel DB
		utenteService.aggiornaUtenteConRuoli(UtenteDTO.buildModelFromDto(utenteDTO), utenteDTO.getIdRuoli());

		// vado in pagina con OK
		request.setAttribute("listaUtentiAttr", utenteService.listAllEager());
		request.setAttribute("messaggioConferma", "Modifica avvenuta con successo");
		request.getRequestDispatcher("/admin/gestioneutenti/result.jsp").forward(request, response);
	}

}

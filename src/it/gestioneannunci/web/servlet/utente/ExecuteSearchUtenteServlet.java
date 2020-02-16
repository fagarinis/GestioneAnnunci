package it.gestioneannunci.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.gestioneannunci.model.Utente;
import it.gestioneannunci.service.UtenteService;

/**
 * Servlet implementation class ExecuteSearchUtenteServlet
 */
@WebServlet("/admin/gestioneutenti/ExecuteSearchUtenteServlet")
public class ExecuteSearchUtenteServlet extends HttpServlet {
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
	public ExecuteSearchUtenteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String usernameInput = request.getParameter("usernameInput");
		String emailInput = request.getParameter("emailInput");
		String statoInput = request.getParameter("statoInput");

		Utente utenteExample = new Utente();
		utenteExample.setNome(nomeInput);
		utenteExample.setCognome(cognomeInput);
		utenteExample.setUsername(usernameInput);
		utenteExample.setEmail(emailInput);
		utenteExample.setStato(statoInput);

		request.setAttribute("listaUtentiAttr", utenteService.findByExampleEager(utenteExample));
		request.getRequestDispatcher("/admin/gestioneutenti/result.jsp").forward(request, response);
	}

}

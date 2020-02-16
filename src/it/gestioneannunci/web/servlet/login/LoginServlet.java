package it.gestioneannunci.web.servlet.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.gestioneannunci.model.Utente;
import it.gestioneannunci.service.CategoriaService;
import it.gestioneannunci.service.UtenteService;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private CategoriaService categoriaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usernameInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");

		Utente utenteCheAccede = utenteService.eseguiAccesso(usernameInput, passwordInput);

		// se non trovo nulla non deve essere possibile accedere
		if (utenteCheAccede == null) {
			request.setAttribute("messaggioErrore", "Login Errato");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}

		// metto utente in sessione
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", utenteCheAccede);
		
		if(session.getAttribute("lastPathBeforeLogin")!=null) {
			request.getRequestDispatcher((String) session.getAttribute("lastPathBeforeLogin")).forward(request, response);
			session.removeAttribute("lastPathBeforeLogin");
			return;
		}
		
		
		request.setAttribute("categorieAttr", categoriaService.listAll());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

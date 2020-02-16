package it.gestioneannunci.web.servlet.areariservata;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestioneannunci.model.Utente;

/**
 * Servlet implementation class PrepareModificaDaAreaRiservataUtenteServlet
 */
@WebServlet("/areariservata/PrepareModificaDaAreaRiservataUtenteServlet")
public class PrepareModificaDaAreaRiservataUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareModificaDaAreaRiservataUtenteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//prendo l'utente in sessione
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		request.setAttribute("utenteAttr",  (Utente) httpRequest.getSession().getAttribute("userInfo"));
		
		request.getRequestDispatcher("/areariservata/modifica.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

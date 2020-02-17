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
import it.gestioneannunci.service.AnnuncioService;
import it.gestioneannunci.service.UtenteService;

/**
 * Servlet implementation class PrepareCompraAnnuncioServlet
 */
@WebServlet("/PrepareCompraAnnuncioServlet")
public class PrepareCompraAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AnnuncioService annuncioService;

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
	public PrepareCompraAnnuncioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idAnnuncioDaPagina = request.getParameter("idAnnuncioBuy");
		if(idAnnuncioDaPagina == null) {
			
			HttpSession session = ((HttpServletRequest) request).getSession();
			idAnnuncioDaPagina = (String) session.getAttribute("lastBuyIdBeforeLogin");
		}

		Annuncio annuncio = annuncioService.caricaAnnuncioEager(Long.parseLong(idAnnuncioDaPagina));
		request.setAttribute("usernameAttr", utenteService.cercaUsernameDaAnnuncio(annuncio));
		request.setAttribute("annuncioAttr", annuncio);
		request.getRequestDispatcher("/acquisto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

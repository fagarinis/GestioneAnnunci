package it.gestioneannunci.web.servlet.annuncio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.gestioneannunci.service.AnnuncioService;

/**
 * Servlet implementation class ExecuteSearchAnnuncioServlet
 */
@WebServlet("/ExecuteSearchAnnuncioServlet")
public class ExecuteSearchAnnuncioServlet extends HttpServlet {
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
	public ExecuteSearchAnnuncioServlet() {
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
		List<String> idCategorieInput = new ArrayList<>();
		String nomeAnnuncioInput = request.getParameter("nomeAnnuncioInput") == null? "" : request.getParameter("nomeAnnuncioInput");

		try {
			idCategorieInput = Arrays.asList(request.getParameterValues("categoriaInput"));
		} catch (Exception e) {
		}

		

		
		
		request.setAttribute("listaAnnunciAttr", annuncioService.cercaTuttiDaTestoEIdCategorie(nomeAnnuncioInput, idCategorieInput));
		request.getRequestDispatcher("/result.jsp").forward(request, response);

	}

}

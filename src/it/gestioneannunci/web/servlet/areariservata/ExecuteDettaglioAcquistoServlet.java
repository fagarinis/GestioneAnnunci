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

import it.gestioneannunci.service.AcquistoService;

/**
 * Servlet implementation class ExecuteDettaglioAcquistoServlet
 */
@WebServlet("/areariservata/ExecuteDettaglioAcquistoServlet")
public class ExecuteDettaglioAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AcquistoService acquistoService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDettaglioAcquistoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAcquistoDaPagina = request.getParameter("idAcquisto");
		request.setAttribute("acquistoAttr", acquistoService.caricaSingolo(Long.parseLong(idAcquistoDaPagina)));
		request.getRequestDispatcher("/areariservata/dettaglioacquisto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

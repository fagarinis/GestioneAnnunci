package it.gestioneannunci.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestioneannunci.model.Utente;

/**
 * Servlet Filter implementation class CheckAuthFilter
 */
@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter implements Filter {

	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = { "/login.jsp", "/registrazione.jsp", "HomeActionServlet",
			"/LoginServlet","ExecuteRegistrazioneUtenteServlet", "/LogoutServlet", "/css/", "/js/" };
	private static final String[] PROTECTED_URLS = { "/admin/" };

	public CheckAuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// prendo il path della request che sta passando in questo momento
		String pathAttuale = httpRequest.getServletPath();
		
		if(isPathDeletingLastPathBeforeLogin(pathAttuale)) {
			HttpSession session = ((HttpServletRequest) request).getSession();
			session.removeAttribute("lastPathBeforeLogin");
		};

		// vediamo se il path risulta tra quelli 'liberi di passare'
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);

		// se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isInWhiteList) {
			Utente utenteInSession = (Utente) httpRequest.getSession().getAttribute("userInfo");
			// intanto verifico se utente in sessione
			if (utenteInSession == null) {
				
				//salvo l'ultimo path richiesto a cui serve la login per accedere
				//e lo salvo in sessione per consumarlo dopo una login corretta
				String lastPathBeforeLogin = pathAttuale;
				HttpSession session = ((HttpServletRequest) request).getSession();
				session.setAttribute("lastPathBeforeLogin", lastPathBeforeLogin);
				
				//se non e' in sessione lo rimando alla pagina login
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.jsp");
				return;
			}
			// controllo che utente abbia ruolo admin se nel path risulta presente /admin/
			if (isPathForOnlyAdministrators(pathAttuale) && !utenteInSession.isAdmin()) {
				httpRequest.setAttribute("messaggioUtenteNonAbilitato",
						"Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/index.jsp").forward(httpRequest, httpResponse);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private boolean isPathInWhiteList(String requestPath) {
		// bisogna controllare che se il path risulta proprio "" oppure se
		// siamo in presenza un url 'libero'
		if (requestPath.equals(HOME_PATH))
			return true;

		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @return true se l'utente richiede una pagina che cancella la memorizzazione
	 * del path richiesto dall'utente a cui serve una sessione attiva per entrare
	 * ad esempio, cliccando su area riservata senza login, prova ad andare nell'area riservata
	 * ma invece, viene salvato il path a cui stava andando l'utente fino a che il login 
	 * ha succcesso. Se l'utente clicca su altri link, il path memorizzato viene cancellato
	 */
	private boolean isPathDeletingLastPathBeforeLogin(String path) {
		if(path.contains("/js/") || path.contains("/css/")) {
			return false;
		}
		if(path.contains("/login.jsp") || path.contains("/registrazione.jsp")) {
			return false;
		}
		if(path.contains("/LoginServlet") || path.contains("/ExecuteRegistrazioneUtenteServlet")) {
			return false;
		}
		
		return true;
		
	}

	public void destroy() {
	}

}
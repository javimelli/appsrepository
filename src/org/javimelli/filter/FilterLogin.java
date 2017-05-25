package org.javimelli.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterLogin
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
,          urlPatterns = { "/resources/*" })
public class FilterLogin implements Filter {
	
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

    /**
     * Default constructor. 
     */
    public FilterLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    
	    HttpSession session = ((HttpServletRequest)request).getSession(true);
		logger.info("Comprobamos si hay sesion");//Si no hay sesion a logearse
		
		if((req.getRequestURI().contains("/resources/apps")&&(req.getMethod().equalsIgnoreCase("GET"))) ||
				req.getRequestURI().contains("/ApiRestRepositorio/resources/session"))
	    {
		    	System.out.println("Entamos en dofilter" + req.getRequestURI());
		    	chain.doFilter(request, response);
	    }else{
	    	System.out.println("Entamos en dofilter" + req.getRequestURI());
			if(session.getAttribute("user") == null){
	    		System.out.println("Entamos en login");
	    		res.setStatus(401);
	    	}
	    	else{
	    		System.out.println("Entamos en dofilter" + req.getRequestURI());
	    		chain.doFilter(request, response);
	    	}	
	    }

		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

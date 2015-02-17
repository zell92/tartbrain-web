package it.uniroma3.dia.tartbrain.util;


import it.uniroma3.dia.tartbrain.controller.UserController;

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

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, description = "Session Checker Filter")
public class SessionCheckerFilter implements Filter {
	private FilterConfig config = null;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		config.getServletContext().log("Initializing SessionCheckerFilter");
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain)
					throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = ((HttpServletRequest) request).getSession(false);
		UserController userController = (session != null) ? (UserController) session.getAttribute("userController") : null;


		if(userController!=null){
			if(!request.getRequestURI().endsWith("login.xhtml") && !userController.isAUTHENTICATED()){
				response.sendRedirect(request.getContextPath() + "/faces/login.xhtml");
			} 
			
			if(request.getRequestURI().endsWith("login.xhtml") && userController.isAUTHENTICATED()){
				response.sendRedirect(request.getContextPath() + "/faces/indexU.xhtml");
			} 
		}else
			response.sendRedirect(request.getContextPath() + "/faces/indexU.xhtml");

	
		chain.doFilter(req, res);
	}

	public void destroy() {
		config.getServletContext().log("Destroying SessionCheckerFilter");
	}
}
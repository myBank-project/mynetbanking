package com.myBank.filter;

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

import com.myBank.other.Role;

/**
 * Servlet Filter implementation class adminFilter
 */
@WebFilter("/Admin/*")
public class adminFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (isUserLoggedIn(request)) {
			chain.doFilter(request, response); // Allow request to proceed
		} else {
			// Redirect to login page or send unauthorized response
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private boolean isUserLoggedIn(ServletRequest request) {
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		if(session!=null) {
			Role role =(Role) session.getAttribute("role");
			if (role!=null  && role.name().equals("ADMIN"))
				return true;
			}
		System.out.println("unable to verify user:");
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

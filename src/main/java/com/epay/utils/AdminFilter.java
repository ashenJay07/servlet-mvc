package com.epay.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// @WebFilter("/admin/*")  // Filter for admin pages
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        

        if (session != null && session.getAttribute("admin") != null) {
            chain.doFilter(request, response);  // Allow access
        } else {
        	// Redirect to login if not logged in
            res.sendRedirect("/oop-epay-crud/admin-login?error=true");  
        }
		
	}

}

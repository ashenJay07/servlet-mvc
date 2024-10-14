package com.epay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epay.model.users.Admin;

// @WebServlet("/admin-validate")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Admin admin = Admin.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var result = admin.validate(request.getParameter("email"), request.getParameter("password"));
		

		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", true);
			response.sendRedirect("/oop-epay-crud/admin/transactions");
		} else {
			response.sendRedirect("/oop-epay-crud/admin-login?error=truez");
		}
	}

}

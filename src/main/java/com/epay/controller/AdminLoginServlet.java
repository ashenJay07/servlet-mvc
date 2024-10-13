package com.epay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epay.model.users.Admin;

// @WebServlet("/admin-validate")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin = Admin.getInstance();
		var result = admin.login(request.getParameter("email"), request.getParameter("password"));

		if (result)
			response.sendRedirect("transactions");
		else
			response.sendRedirect("admin-login");
	}

}

package com.epay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epay.dao.UserRequestDAO;


// @WebServlet("/upgrade-package")
public class UserRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = "UID0001";
		String packageName = request.getParameter("package-name");
		
		if (request.getServletPath() == "/upgrade-package") {
			UserRequestDAO.makeUpgradeRequest(packageName, userId);
		}
		else if (request.getServletPath() == "/deactivate-package") {
			UserRequestDAO.makeDeactivationRequest(packageName, userId);
			response.sendRedirect("packages");
		}
	}
}

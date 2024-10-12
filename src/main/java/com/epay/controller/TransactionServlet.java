package com.epay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epay.dao.AdminDAO;

// @WebServlet("/admin/transactions")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URL parts ---> request.getRequestURL().toString(), request.getContextPath(), request.getServletPath()
		if (request.getServletPath() == "/admin/transactions") {
			request.setAttribute("transactions", AdminDAO.getAllTransactions());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin/transactions.jsp");
	        dispatcher.forward(request, response);
		}
		if (request.getServletPath() == "/admin/activated-packages") {
			request.setAttribute("activePackages", AdminDAO.getAllActivePackages());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin/active-packages.jsp");
	        dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

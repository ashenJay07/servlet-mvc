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
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URL parts ---> request.getRequestURL().toString(), request.getContextPath(), request.getServletPath()
		if (request.getServletPath() == "/admin/transactions") {
			request.setAttribute("transactions", AdminDAO.getAllTransactions());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin/transactions.jsp");
	        dispatcher.forward(request, response);
		}
		else if (request.getServletPath() == "/admin/activated-packages") {
			request.setAttribute("activePackages", AdminDAO.getAllActivePackages());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin/active-packages.jsp");
	        dispatcher.forward(request, response);
		}
		else if (request.getServletPath() == "/admin/request/upgrade-requests") {
			request.setAttribute("upgradeRequests", AdminDAO.getRequestedPackages("upgrade"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin/upgrade-requests.jsp");
	        dispatcher.forward(request, response);
		}
		else if (request.getServletPath() == "/admin/request/deactivation-requests") {
			request.setAttribute("deactivationRequests", AdminDAO.getRequestedPackages("deactivate"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin/deactivation-requests.jsp");
	        dispatcher.forward(request, response);
		}
		else if (request.getServletPath() == "/admin/request/deactivate") {
			int packageId = Integer.parseInt(request.getParameter("packageId"));
			String packageName = request.getParameter("packageName");
			String userId = request.getParameter("userId");
			
			System.out.println(packageId + " " + userId);
			
			AdminDAO.deactivatePackage(packageId, packageName, userId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin/deactivation-requests.jsp");
	        dispatcher.forward(request, response);
		}
		else if (request.getServletPath() == "/admin/request/upgrade") {
			int packageId = Integer.parseInt(request.getParameter("packageId"));
			String packageName = request.getParameter("packageName");
			String userId = request.getParameter("userId");
			
			AdminDAO.upgradePackage(packageId, packageName, userId);
			response.sendRedirect("upgrade-requests");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

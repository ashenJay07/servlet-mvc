package com.epay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/submit-payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String cardHolder = request.getParameter("card-holder");
		String cardNumber = request.getParameter("card-number");
		String expMonth = request.getParameter("exp-month");
		String expYear = request.getParameter("exp-year");
		int cvv = Integer.parseInt(request.getParameter("cvv"));
		
		System.out.println(email + " - " + cardHolder + " - " + cardNumber + " - " + expMonth + " - " + expYear + " - " + cvv);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/payment.jsp");
        dispatcher.forward(request, response);
	}

}

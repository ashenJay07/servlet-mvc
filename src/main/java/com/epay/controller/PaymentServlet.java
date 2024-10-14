package com.epay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epay.dao.PaymentDAO;
import com.epay.model.PaymentInfo;
import com.epay.utils.AESEncryption;

// @WebServlet("/submit-payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("packages");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (request.getServletPath() == "/checkout") {
			boolean isPending = Boolean.parseBoolean(request.getParameter("upgrade"));
			int pkgDuration = Integer.parseInt(request.getParameter("duration"));
			float pkgPrice = 0;
			
			if (pkgDuration == 7)
				pkgPrice = Float.parseFloat(request.getParameter("weekly-package-price"));
			else		
				pkgPrice = Float.parseFloat(request.getParameter("monthly-package-price"));
			
			
			if (isPending) {
				pkgPrice = Float.parseFloat(request.getParameter("monthly-package-price"));
				pkgDuration = 30;
			}
			
			request.setAttribute("packageName", request.getParameter("package-name"));
			request.setAttribute("packageDuration", pkgDuration);
			request.setAttribute("packagePrice", pkgPrice);
			request.setAttribute("pending", isPending);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/payment.jsp");
	        dispatcher.forward(request, response);
		}
		else if (request.getServletPath() == "/submit-payment") {
			
			PaymentInfo paymentInstance = new PaymentInfo();
			AESEncryption aesEncryption;
			
			try {
				aesEncryption = new AESEncryption();
				
				String cardNumber = request.getParameter("card-number");
				cardNumber = cardNumber.replaceAll(" - ", "");
				
				paymentInstance.setPackageName(request.getParameter("package-name"));
				paymentInstance.setTransAmount(Float.parseFloat(request.getParameter("transaction-amount")));
				paymentInstance.setPackageDuration(Integer.parseInt(request.getParameter("package-duration")));
				paymentInstance.setEmail(request.getParameter("email"));
				paymentInstance.setCardHolder(request.getParameter("card-holder"));
				paymentInstance.setCardNumber(aesEncryption.encrypt(cardNumber));
				paymentInstance.setCardExpMonth(Integer.parseInt(request.getParameter("exp-month")));
				paymentInstance.setCardExpYear(Integer.parseInt(request.getParameter("exp-year")));
				paymentInstance.setCvv(aesEncryption.encrypt(request.getParameter("cvv")));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			PaymentDAO.makePayment(paymentInstance, Boolean.parseBoolean(request.getParameter("pending")));
			response.sendRedirect("packages");
		}
	}
}

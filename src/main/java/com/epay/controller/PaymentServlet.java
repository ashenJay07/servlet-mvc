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

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaymentInfo paymentInstance = new PaymentInfo();
		AESEncryption aesEncryption;
		
		try {
			aesEncryption = new AESEncryption();
			
			paymentInstance.setTransAmount(Float.parseFloat("263.50"));
			paymentInstance.setEmail(request.getParameter("email"));
			paymentInstance.setCardHolder(request.getParameter("card-holder"));
			paymentInstance.setCardNumber(aesEncryption.encrypt(request.getParameter("card-number")));
			paymentInstance.setCardExpMonth(Integer.parseInt(request.getParameter("exp-month")));
			paymentInstance.setCardExpYear(Integer.parseInt(request.getParameter("exp-year")));
			paymentInstance.setCvv(aesEncryption.encrypt(request.getParameter("cvv")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PaymentDAO.makePayment(paymentInstance);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/payment.jsp");
        dispatcher.forward(request, response);
	}

}

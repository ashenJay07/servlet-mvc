package com.itpproject.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.itpproject.dao.PackageDAO;
import com.itpproject.model.Package;

public class PackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PackageDAO packageDAO;

	public void init() {
		packageDAO = new PackageDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Hello");
		List<Package> pkgNames = packageDAO.getAllProducts();

		for (Package p : pkgNames)
			System.out.println(p.getPackageName());;
		
        request.setAttribute("productList", pkgNames);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("views/productList.jsp");
//        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

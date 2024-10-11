package com.epay.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.epay.dao.PackageDAO;
import com.epay.utils.packages.FunBlaster;
import com.epay.utils.packages.UnlimitedBlaster;


// @WebServlet("/packages")
public class PackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private PackageDAO packageDAO;

    public PackageServlet() {
        super();
    }
    
//    public void init() {
//		packageDAO = new PackageDAO();
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<Package> pkgNames = packageDAO.getAllProducts();
		List<Object> pkgInstances = new ArrayList<>();

//		System.out.println(UnlimitedBlaster.getInstance().getPackageName());
		
		pkgInstances.add(UnlimitedBlaster.getInstance());
		pkgInstances.add(FunBlaster.getInstance());
		pkgInstances.add(UnlimitedBlaster.getInstance());
		pkgInstances.add(FunBlaster.getInstance());
		
        request.setAttribute("pkgInstances", pkgInstances);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

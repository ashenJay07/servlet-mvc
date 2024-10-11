package com.epay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.epay.utils.DatabaseConfig;
import com.epay.model.Package;

public class PackageDAO {
//	private String SELECT_ALL_PACKAGE_NAMES = "SELECT package_name FROM package";
	private String SELECT_ALL_PACKAGE = "SELECT * FROM package";
	
	
//	public List<Package> getAllProductNames() { 
//		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
//		List<Package> packageNames = new ArrayList<>();
//		
//		try(Connection connection = dbInstance.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PACKAGE_NAMES)) {
//			
//			ResultSet rs = preparedStatement.executeQuery();
//			
//            while (rs.next()) {
//                String productName = rs.getString("package_name");
//                packageNames.add(new Package(productName));
//            } 
//		} catch(Exception e) {
//        	
//        }
//		
//		return packageNames;
//	}
	
	public List<Package> getAllProducts() {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		List<Package> packages = new ArrayList<>();
		
		try(Connection connection = dbInstance.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PACKAGE)) {
			
			ResultSet rs = preparedStatement.executeQuery();
			
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("package_name");
                float price = rs.getFloat("package_price");
                packages.add(new Package(id, productName, price));
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return packages;
	}
}

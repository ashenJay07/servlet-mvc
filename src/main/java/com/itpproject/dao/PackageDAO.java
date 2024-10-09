package com.itpproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itpproject.utils.DatabaseConfig;
import com.itpproject.model.Package;

public class PackageDAO {
	private String SELECT_ALL_PRODUCTS = "SELECT * FROM package";
	
	public List<Package> getAllProducts() {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		List<Package> packages = new ArrayList<>();
		
		try(Connection connection = dbInstance.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
			
			ResultSet rs = preparedStatement.executeQuery();
			
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("product_name");
                float price = rs.getFloat("price");
                packages.add(new Package(id, productName, price));
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return packages;
	}
}

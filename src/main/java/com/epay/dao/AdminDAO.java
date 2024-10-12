package com.epay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.epay.model.Package;
import com.epay.model.PaymentInfo;
import com.epay.utils.DatabaseConfig;

public class AdminDAO {
	private static String GET_ALL_TRANSACTIONS = "SELECT * FROM transaction_info";
	private static String GET_ALL_ACTIVE_PACKAGES = "SELECT * FROM activated_packages";
	
	
	public static List<PaymentInfo> getAllTransactions() {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		List<PaymentInfo> transactions = new ArrayList<>();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TRANSACTIONS);
			ResultSet rs = preparedStatement.executeQuery();
			
            while (rs.next()) {
                int id = rs.getInt("trans_id");
                float amount = rs.getFloat("trans_amount");
                String cardHolder = rs.getString("card_holder");
                String email = rs.getString("email");
                String tranDate = rs.getString("trans_date");
                String userId = rs.getString("user_id");
                
                transactions.add(new PaymentInfo(id, amount, cardHolder, email, tranDate, userId));
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return transactions;
	}
	
	public static List<PaymentInfo> getAllActivePackages() {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		List<PaymentInfo> activePackages = new ArrayList<>();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ACTIVE_PACKAGES);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
                int pkgId = rs.getInt("package_id");
                int pkgDuration = rs.getInt("duration");
                String activatedDate = rs.getString("activated_date");
                String expDate = rs.getString("expire_date");
                String userId = rs.getString("user_id");
                
                activePackages.add(new PaymentInfo(id, userId, pkgId, pkgDuration));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return activePackages;
		
	}
}

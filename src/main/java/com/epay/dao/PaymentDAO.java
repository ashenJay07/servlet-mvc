package com.epay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.epay.model.Package;
import com.epay.model.PaymentInfo;
import com.epay.utils.DatabaseConfig;

public class PaymentDAO {
	
	// SQL Queries
	private static String INSERT_TRANSACTION_INFO = "INSERT INTO transaction_info (trans_amount, card_holder, email, "
			+ "card_no, card_exp_month, card_exp_year, cvv, trans_date, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	
	public static int makePayment(PaymentInfo formData) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement stmtTransaction = connection.prepareStatement(INSERT_TRANSACTION_INFO);
			
			stmtTransaction.setFloat(1, formData.getTransAmount());
			stmtTransaction.setString(2, formData.getCardHolder());
			stmtTransaction.setString(3, formData.getEmail());
			stmtTransaction.setString(4, formData.getCardNumber());
			stmtTransaction.setInt(5, formData.getCardExpMonth());
			stmtTransaction.setInt(6, formData.getCardExpYear());
			stmtTransaction.setString(7, formData.getCvv());
			stmtTransaction.setString(8, "2024-10-05");
			stmtTransaction.setString(9, "UID0001");
			
			int rowsInsertedDetails = stmtTransaction.executeUpdate();
			
			if (rowsInsertedDetails > 0) {
				System.out.println("Insertion Successful !!!");
				return 1;
			}
			else
				System.out.println("Insertion Unsuccessfull !!!");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}

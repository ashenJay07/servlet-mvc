package com.epay.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epay.model.Package;
import com.epay.model.PaymentInfo;
import com.epay.utils.DatabaseConfig;

public class PaymentDAO {
	
	// SQL Queries
	private static String INSERT_TRANSACTION_INFO = "INSERT INTO transaction_info (trans_amount, card_holder, email, "
			+ "card_no, card_exp_month, card_exp_year, cvv, trans_date, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String INSERT_NEWLY_ACTIVATED_PACKAGE = "INSERT INTO activated_packages (package_id, duration, "
			+ "activated_date, expire_date, user_id) VALUES (?, ?, ?, ?, ?)";
	
	
	public static int makePayment(PaymentInfo formData) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			// Turn off auto-commit for transaction management
            connection.setAutoCommit(false);
			
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
			
			int rowsInsertedTransactions = stmtTransaction.executeUpdate();
			
			if (rowsInsertedTransactions > 0) {
				PreparedStatement stmtActivePackage = connection.prepareStatement(INSERT_NEWLY_ACTIVATED_PACKAGE);
				
				LocalDate activationDate = LocalDate.now();
				LocalDate expireDate = activationDate.plusDays(formData.getPackageDuration());
				
				int packageId = getPackgeId(formData.getPackageName());
				
				if (packageId <= 0)
					return 0;
				
				stmtActivePackage.setInt(1, packageId);
				stmtActivePackage.setInt(2, formData.getPackageDuration());
				stmtActivePackage.setDate(3, Date.valueOf(activationDate));
				stmtActivePackage.setDate(4, Date.valueOf(expireDate));
				stmtActivePackage.setString(5, "UID0001");
				
				int rowsInsertedPackages = stmtActivePackage.executeUpdate();
//				int rowsInsertedPackages = 0;
				
				if (rowsInsertedPackages > 0) {
					connection.commit();
					System.out.println("Data inserted to both tables successfully !!!");
					return 1;
				} else {
					connection.rollback();
					System.err.println("Data insertion unsuccessful !!!");
				}
			}
			else
				System.out.println("Insertion Unsuccessfull !!!");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private static int getPackgeId(String packageName) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		System.out.println(packageName);
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement stmt = connection.prepareStatement("SELECT id FROM package WHERE package_name = ?");
			stmt.setString(1, packageName);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				return rs.getInt("id");				
			}
			throw new Error("Package ID not found");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}

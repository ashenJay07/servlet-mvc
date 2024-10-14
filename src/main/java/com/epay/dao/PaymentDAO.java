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
import com.epay.model.users.User;
import com.epay.utils.DatabaseConfig;

public class PaymentDAO {
	
	// SQL Queries
	private static String INSERT_TRANSACTION_INFO = "INSERT INTO transaction_info (trans_amount, card_holder, email, "
			+ "card_no, card_exp_month, card_exp_year, cvv, package_id, trans_date, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String INSERT_PENDING_TRANSACTION_INFO = "INSERT INTO transaction_info (trans_amount, card_holder, email, "
			+ "card_no, card_exp_month, card_exp_year, cvv, package_id, trans_date, user_id, is_pending) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, true)";
	private static String INSERT_NEWLY_ACTIVATED_PACKAGE = "INSERT INTO activated_packages (package_id, duration, "
			+ "activated_date, expire_date, user_id) VALUES (?, ?, ?, ?, ?)";
	private static String MAKE_UPGRADE_REQUEST = "UPDATE activated_packages SET upgrade_request = 1 WHERE package_id = ? AND user_id = ?;";
	
	
	public static int makePayment(PaymentInfo formData, boolean isPending) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			// Turn off auto-commit for transaction management
            connection.setAutoCommit(false);
            
            LocalDate todayDate = LocalDate.now();
            
            PreparedStatement stmtTransaction = connection.prepareStatement(INSERT_TRANSACTION_INFO);
            
            String userId = User.getInstance().getUserId();
            int packageId = getPackageId(formData.getPackageName());
            
            if (isPending)
            	stmtTransaction = connection.prepareStatement(INSERT_PENDING_TRANSACTION_INFO);
			
			stmtTransaction.setFloat(1, formData.getTransAmount());
			stmtTransaction.setString(2, formData.getCardHolder());
			stmtTransaction.setString(3, formData.getEmail());
			stmtTransaction.setString(4, formData.getCardNumber());
			stmtTransaction.setInt(5, formData.getCardExpMonth());
			stmtTransaction.setInt(6, formData.getCardExpYear());
			stmtTransaction.setString(7, formData.getCvv());
			stmtTransaction.setInt(8, packageId);
			stmtTransaction.setDate(9, Date.valueOf(todayDate));
			stmtTransaction.setString(10, userId);
			
			int rowsInsertedTransactions = stmtTransaction.executeUpdate();
			
			if (rowsInsertedTransactions > 0) {
				int numOfRowsAffected = 0;
				
				if (!isPending) {
					numOfRowsAffected = insertNewlyActivatedPackage(formData, packageId);			
				} else {
					numOfRowsAffected = makeUpgradeRequest(formData.getPackageName(), userId);
				}
				
				if (numOfRowsAffected > 0) {
					connection.commit();
					return 1;
				}
				else {
					connection.rollback();
					System.err.println("Data insertion unsuccessful !!!");
				}

			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	private static int insertNewlyActivatedPackage(PaymentInfo formData, int packageId) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			PreparedStatement stmtActivePackage = connection.prepareStatement(INSERT_NEWLY_ACTIVATED_PACKAGE);
			
			String userId = User.getInstance().getUserId();
			LocalDate activationDate = LocalDate.now();
			LocalDate expireDate = activationDate.plusDays(formData.getPackageDuration());
			
			if (packageId <= 0)
				return 0;
			
			stmtActivePackage.setInt(1, packageId);
			stmtActivePackage.setInt(2, formData.getPackageDuration());
			stmtActivePackage.setDate(3, Date.valueOf(activationDate));
			stmtActivePackage.setDate(4, Date.valueOf(expireDate));
			stmtActivePackage.setString(5, userId);
			
			int rowsInsertedPackages = stmtActivePackage.executeUpdate();
			
			if (rowsInsertedPackages > 0) {
				return rowsInsertedPackages;
			} else {
				System.err.println("Data insertion unsuccessful !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public static int makeUpgradeRequest(String packageName, String userId) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			int packageId = getPackageId(packageName);
			
			PreparedStatement stmt = connection.prepareStatement(MAKE_UPGRADE_REQUEST);
			stmt.setInt(1, packageId);
			stmt.setString(2, userId);
			
			int rowsUpdated = stmt.executeUpdate();
			
			if (rowsUpdated > 0)
				return rowsUpdated;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	private static int getPackageId(String packageName) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
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

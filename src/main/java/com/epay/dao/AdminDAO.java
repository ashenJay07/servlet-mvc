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
import com.epay.utils.packages.EDoc;
import com.epay.utils.packages.FunBlaster;
import com.epay.utils.packages.IPackage;
import com.epay.utils.packages.PackageFactory;
import com.epay.utils.packages.Roaming;
import com.epay.utils.packages.UnlimitedBlaster;
import com.epay.utils.packages.UpaharaService;

public class AdminDAO {
	private static String GET_ALL_TRANSACTIONS = "SELECT * FROM transaction_info";
	private static String GET_ALL_ACTIVE_PACKAGES = "SELECT * FROM activated_packages;";
	private static String GET_UPGRADE_REQUESTED_PACKAGES = "SELECT ap.package_id, package_name, duration, activated_date, expire_date, "
			+ "user_id FROM activated_packages ap LEFT JOIN package p ON p.id = ap.package_id WHERE upgrade_request = 1;";
	private static String GET_DEACTIVATION_REQUESTED_PACKAGES = "SELECT ap.package_id, package_name, duration, activated_date, "
			+ "expire_date, user_id FROM activated_packages ap LEFT JOIN package p ON p.id = ap.package_id WHERE deactivation_request = 1;";
	private static String DEACTIVATE_PACKAGE_BY_ID = "DELETE FROM activated_packages WHERE package_id = ? AND user_id = ?;";
	private static String UPGRADE_PACKAGE_BY_ID = "UPDATE activated_packages SET duration = 30, activated_date = ?, expire_date = ?, upgrade_request = null WHERE package_id = ? AND user_id = ?;";
	
	
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
	
	
//	public static List<PaymentInfo> getUpgradeRequestedPackages(String reqType) {
//		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
//		List<PaymentInfo> packages = new ArrayList<>();
//		
//		try(Connection connection = dbInstance.getConnection()) {
//			
//			PreparedStatement preparedStatement = connection.prepareStatement(GET_UPGRADE_REQUESTED_PACKAGES);
//			ResultSet rs = preparedStatement.executeQuery();
//			
//			while (rs.next()) {
//                int pkgId = rs.getInt("package_id");
//                String pkgName = rs.getString("package_name");
//                int pkgDuration = rs.getInt("duration");
//                Date activatedDate = rs.getDate("activated_date");
//                Date expDate = rs.getDate("expire_date");
//                String userId = rs.getString("user_id");
//                
//                packages.add(new PaymentInfo(pkgName, userId, pkgId, pkgDuration, activatedDate, expDate));
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return packages;
//	}
	
	
	public static List<PaymentInfo> getRequestedPackages(String reqType) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		List<PaymentInfo> packages = new ArrayList<>();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement preparedStatement = null;
			
			if (reqType == "upgrade")
				preparedStatement = connection.prepareStatement(GET_UPGRADE_REQUESTED_PACKAGES);
			else if (reqType == "deactivate")
				preparedStatement = connection.prepareStatement(GET_DEACTIVATION_REQUESTED_PACKAGES);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
                int pkgId = rs.getInt("package_id");
                String pkgName = rs.getString("package_name");
                int pkgDuration = rs.getInt("duration");
                Date activatedDate = rs.getDate("activated_date");
                Date expDate = rs.getDate("expire_date");
                String userId = rs.getString("user_id");
                
                packages.add(new PaymentInfo(pkgName, userId, pkgId, pkgDuration, activatedDate, expDate));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return packages;
	}
	
	
	public static int deactivatePackage(int pkgId, String packageName, String userId) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement stmt = connection.prepareStatement(DEACTIVATE_PACKAGE_BY_ID);
			stmt.setInt(1, pkgId);
			stmt.setString(2, userId);
			
			// Execute the query
			int rowsDeleted = stmt.executeUpdate();
			
			if (rowsDeleted > 0)
				PackageFactory.getPackageInstance(packageName).setCurrentlyActiveDuration(0);
				return 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static int upgradePackage(int pkgId, String packageName, String userId) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			LocalDate activationDate = LocalDate.now();
			LocalDate expireDate = activationDate.plusDays(30);
			
			connection.setAutoCommit(false);
			
			PreparedStatement stmt0 = connection.prepareStatement("UPDATE transaction_info SET is_pending = null WHERE package_id = ? "
					+ "AND user_id = ? AND is_pending = 1");
			stmt0.setInt(1, pkgId);
			stmt0.setString(2, userId);
			
			int rowsUpdated = stmt0.executeUpdate();
			
//			float monthlyPackagePrice = getPackgePrice(pkgId);
			
			if (rowsUpdated > 0) {
				PreparedStatement stmt = connection.prepareStatement(UPGRADE_PACKAGE_BY_ID);
				stmt.setDate(1, Date.valueOf(activationDate));
				stmt.setDate(2, Date.valueOf(expireDate));
				stmt.setInt(3, pkgId);
				stmt.setString(4, userId);
				
//				System.out.println("Admin - " + getPackageInstance(packageName).getWeeklyPackagePrice());
//				System.out.println("Admin - " + getPackageInstance(packageName).getMonthlyPackagePrice());
				
				rowsUpdated = stmt.executeUpdate();
				
				if (rowsUpdated > 0) {
					connection.commit();
					System.out.println("Package upgraded successfully !!!");
					return 1;
				}
				else {
					connection.rollback();
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private static float getPackgePrice(int pkgId) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement stmt = connection.prepareStatement("SELECT package_price_month FROM package WHERE id = ?");
			stmt.setInt(1, pkgId);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				return rs.getFloat("package_price_month");				
			}
			throw new Error("Package price not found");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
//	private static IPackage getPackageInstance(String packageType) {
//		IPackage instance = null;
//		
//		if (packageType.equalsIgnoreCase("Fun Blaster")) {
//			instance = FunBlaster.getInstance();
//		}
//		else if (packageType.equalsIgnoreCase("Unlimited Blaster")) {
//			instance = UnlimitedBlaster.getInstance();
//		}
//		else if (packageType.equalsIgnoreCase("Roaming")) {
//			instance = Roaming.getInstance();
//		}
//		else if (packageType.equalsIgnoreCase("E-Doc Service")) {
//			instance = EDoc.getInstance();
//		}
//		else if (packageType.equalsIgnoreCase("Upahara Service")) {
//			instance = UpaharaService.getInstance();
//		}
//		
//		return instance;
//	}
}

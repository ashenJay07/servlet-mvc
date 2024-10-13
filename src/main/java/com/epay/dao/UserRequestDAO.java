package com.epay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.epay.utils.DatabaseConfig;

public class UserRequestDAO {
	private static String GET_PACKAGE_ID_BY_NAME = "SELECT id FROM package WHERE package_name = ?;";
	private static String MAKE_UPGRADE_REQUEST = "UPDATE activated_packages SET upgrade_request = 1 WHERE package_id = ? AND user_id = ?;";
	private static String MAKE_DEACTIVATION_REQUEST = "UPDATE activated_packages SET deactivation_request = 1 WHERE package_id = ? AND user_id = ?;";
	
	public static int makeUpgradeRequest(String packageName, String userId) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			int packageId = getPackageId(packageName);
			
			PreparedStatement stmt = connection.prepareStatement(MAKE_UPGRADE_REQUEST);
			stmt.setInt(1, packageId);
			stmt.setString(2, userId);
			
			int rowsUpdated = stmt.executeUpdate();
			
			if (rowsUpdated > 0)
				System.out.println("Upgrade Requested !!!");
				return 1;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public static boolean makeDeactivationRequest(String packageName, String userId) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			int packageId = getPackageId(packageName);
			
			PreparedStatement stmt = connection.prepareStatement(MAKE_DEACTIVATION_REQUEST);
			stmt.setInt(1, packageId);
			stmt.setString(2, userId);
			
			int rowsUpdated = stmt.executeUpdate();
			
			if (rowsUpdated > 0)
				System.out.println("Deactivation Requested !!!");
				return true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private static int getPackageId(String packageName) {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		System.out.println(packageName);
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement stmt = connection.prepareStatement(GET_PACKAGE_ID_BY_NAME);
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

package com.epay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.epay.utils.DatabaseConfig;
import com.epay.utils.packages.FunBlaster;
import com.epay.utils.packages.IPackage;
import com.epay.utils.packages.UnlimitedBlaster;
import com.epay.model.Package;

public class PackageDAO {
//	private String SELECT_ALL_PACKAGE_NAMES = "SELECT package_name FROM package";
	private static String SELECT_ALL_PACKAGE = "SELECT * FROM package";
	private static String SELECT_PACKAGE_INFO_BY_USER = "SELECT * FROM activated_packages ap LEFT JOIN package p "
			+ "ON p.id = ap.package_id WHERE user_id = ? UNION SELECT * FROM activated_packages ap RIGHT JOIN package p "
			+ "ON p.id = ap.package_id WHERE user_id = ?";
	
	
	public static List<Package> getAllProducts() {
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
	
	
	public static List<IPackage> getActivePackagesByUser() {
		DatabaseConfig dbInstance = DatabaseConfig.getDBInstance();
		List<IPackage> activePkgList = new ArrayList<>();
		IPackage instance = null;
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement stmtPackages = connection.prepareStatement(SELECT_PACKAGE_INFO_BY_USER);
			
			stmtPackages.setString(1, "UID0001");
			stmtPackages.setString(2, "UID0001");
			
			ResultSet rs = stmtPackages.executeQuery();
			
            while (rs.next()) {
            	String packageName = rs.getString("package_name");
            	
            	if (packageName == null)
            		continue;
            	
//            	float weekPrice = rs.getFloat("package_price_week");
//            	float monthPrice = rs.getFloat("package_price_month");
//            	String currentDuration = rs.getString("duration");
//            	boolean isUpgradeRequested = rs.getInt("upgrade_request") == 1;
//            	boolean isDeactivationRequested = rs.getInt("deactivation_request") == 1;
            	
            	instance = getPackageInstance(packageName);
            	
            	if (instance == null)
            		continue;
            	
            	instance.setWeeklyPackagePrice(rs.getFloat("package_price_week"));
            	instance.setMonthlyPackagePrice(rs.getFloat("package_price_month"));
            	instance.setCurrentlyActiveDuration(rs.getInt("duration"));
            	instance.setUpgradeRequested(rs.getInt("upgrade_request") == 1);
            	instance.setDeactivationRequested(rs.getInt("deactivation_request") == 1);
            	
            	activePkgList.add(instance);
            }
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return activePkgList;
	}
	
	private static IPackage getPackageInstance(String packageType) {
		IPackage instance = null;
		
		if (packageType.equalsIgnoreCase("Fun Blaster")) {
			instance = FunBlaster.getInstance();
		}
		else if (packageType.equalsIgnoreCase("Unlimited Blaster")) {
			instance = UnlimitedBlaster.getInstance();
		}
		
		return instance;
	}
	
	
}

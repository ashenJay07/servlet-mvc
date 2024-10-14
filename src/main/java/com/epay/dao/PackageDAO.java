package com.epay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.epay.utils.DatabaseConfig;
import com.epay.utils.packages.EDoc;
import com.epay.utils.packages.FunBlaster;
import com.epay.utils.packages.IPackage;
import com.epay.utils.packages.PackageFactory;
import com.epay.utils.packages.Roaming;
import com.epay.utils.packages.UnlimitedBlaster;
import com.epay.utils.packages.UpaharaService;
import com.epay.model.Package;
import com.epay.model.users.User;

public class PackageDAO {
	private static String SELECT_ALL_PACKAGE = "SELECT * FROM package";
	private static String SELECT_PACKAGE_INFO_BY_USER = "WITH temp_table AS (SELECT * FROM activated_packages WHERE user_id = ?) "
			+ "SELECT user_id, package_price_week, package_price_month, duration, package_name, upgrade_request, deactivation_request "
			+ "FROM package p LEFT JOIN temp_table t ON t.package_id = p.id;";
	
	
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
		String userId = User.getInstance().getUserId();
		
		try(Connection connection = dbInstance.getConnection()) {
			
			PreparedStatement stmtPackages = connection.prepareStatement(SELECT_PACKAGE_INFO_BY_USER);
			stmtPackages.setString(1, userId);
			
			ResultSet rs = stmtPackages.executeQuery();
			
            while (rs.next()) {
            	String packageName = rs.getString("package_name");
            	
            	if (packageName == null)
            		continue;
            	
            	instance = PackageFactory.getPackageInstance(packageName);
            	
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
	
}

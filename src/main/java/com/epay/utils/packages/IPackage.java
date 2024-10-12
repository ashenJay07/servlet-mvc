package com.epay.utils.packages;

public interface IPackage {
	String getPackageName();
	String[] getPackageDetails();
	float getPackagePrice();
	float getWeeklyPackagePrice();
	float getMonthlyPackagePrice();
	int getCurrentlyActiveDuration();
	boolean isUpgradeRequested();
	boolean isDeactivationRequested();
	
	void setWeeklyPackagePrice(float weeklyPackagePrice);
	void setMonthlyPackagePrice(float monthlyPackagePrice);
	void setCurrentlyActiveDuration(int currentlyActiveDuration);
	void setUpgradeRequested(boolean isUpgradeRequested);
	void setDeactivationRequested(boolean isDeactivationRequested);
}

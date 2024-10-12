package com.epay.utils.packages;

public interface IPackage {
	String getPackageName();
	void setWeeklyPackagePrice(float weeklyPackagePrice);
	void setMonthlyPackagePrice(float monthlyPackagePrice);
	void setCurrentlyActiveDuration(String currentlyActiveDuration);
	void setUpgradeRequested(boolean isUpgradeRequested);
	void setDeactivationRequested(boolean isDeactivationRequested);
}

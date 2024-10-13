package com.epay.utils.packages;

public class Roaming implements IPackage {
	private static Roaming instance;
	
	private String packageName = "Roaming";
	private String[] packageDetails = {
			"5 GB of international data for browsing while abroad, applicable in over 50 countries.",
			"500 minutes of international voice calls to select countries.",
			"Free incoming calls while traveling internationally, with no extra charges."
	};
	private float weeklyPackagePrice = 250.0f;
	private float monthlyPackagePrice = 180.0f;
	private int currentlyActiveDuration;
	private boolean isUpgradeRequested;
	private boolean isDeactivationRequested;
	
	private Roaming() {}
	
	public static Roaming getInstance() {
		if (instance == null)
			instance = new Roaming();
			
		return instance;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public String[] getPackageDetails() {
		return packageDetails;
	}

	public float getWeeklyPackagePrice() {
		return weeklyPackagePrice;
	}

	public float getMonthlyPackagePrice() {
		return monthlyPackagePrice;
	}

	public int getCurrentlyActiveDuration() {
		return currentlyActiveDuration;
	}

	public boolean isUpgradeRequested() {
		return isUpgradeRequested;
	}

	public boolean isDeactivationRequested() {
		return isDeactivationRequested;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setPackageDetails(String[] packageDetails) {
		this.packageDetails = packageDetails;
	}

	public void setWeeklyPackagePrice(float weeklyPackagePrice) {
		this.weeklyPackagePrice = weeklyPackagePrice;
	}

	public void setMonthlyPackagePrice(float monthlyPackagePrice) {
		this.monthlyPackagePrice = monthlyPackagePrice;
	}

	public void setCurrentlyActiveDuration(int currentlyActiveDuration) {
		this.currentlyActiveDuration = currentlyActiveDuration;
	}

	public void setUpgradeRequested(boolean isUpgradeRequested) {
		this.isUpgradeRequested = isUpgradeRequested;
	}

	public void setDeactivationRequested(boolean isDeactivationRequested) {
		this.isDeactivationRequested = isDeactivationRequested;
	}
}
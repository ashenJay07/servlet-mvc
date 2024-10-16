package com.epay.utils.packages;

public class FunBlaster implements IPackage {
	private static FunBlaster instance;
	
	private String packageName = "Fun Blaster";
	private String[] packageDetails = {
			"10 GB of high-speed data for browsing, streaming, and gaming.",
			"1000 minutes of voice calls to local and international numbers (fair usage applies).",
			"Unlimited access to social media platforms (Facebook, WhatsApp, Instagram) without consuming data."
	};
	private float packagePrice = 45.0f;
	private float weeklyPackagePrice = 250.0f;
	private float monthlyPackagePrice = 200.0f;
	private int currentlyActiveDuration;
	private boolean isUpgradeRequested;
	private boolean isDeactivationRequested;
	
	private FunBlaster() {};
	
	public static FunBlaster getInstance() {
		if (instance == null)
			instance = new FunBlaster();
			
		return instance;
	}

	public String getPackageName() {
		return packageName;
	}

	public String[] getPackageDetails() {
		return packageDetails;
	}

	public float getPackagePrice() {
		return packagePrice;
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

	public void setPackagePrice(float packagePrice) {
		this.packagePrice = packagePrice;
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

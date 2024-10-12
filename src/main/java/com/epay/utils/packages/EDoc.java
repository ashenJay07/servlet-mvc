package com.epay.utils.packages;

public class EDoc implements IPackage {
	private static EDoc instance;
	
	private String packageName = "E-Doc Service";
	private String[] packageDetails = {
			"20 GB of high-speed data dedicated to educational platforms (Google Scholar, JSTOR, Coursera).",
			"Free access to document-sharing services like Google Drive, Dropbox, and OneDrive without data usage.",
			"Special browsing rates for online educational content and virtual classrooms."
	};
	private float packagePrice = 50.0f;
	private float weeklyPackagePrice = 750.0f;
	private float monthlyPackagePrice = 630.0f;
	private int currentlyActiveDuration;
	private boolean isUpgradeRequested;
	private boolean isDeactivationRequested;
	
	private EDoc() {}
	
	public static EDoc getInstance() {
		if (instance == null)
			instance = new EDoc();
			
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

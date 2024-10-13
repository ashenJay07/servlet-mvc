package com.epay.utils.packages;

public class UpaharaService implements IPackage {
	private static UpaharaService instance;
	
	private String packageName = "Upahara Service";
	private String[] packageDetails = {
			"15 GB of data for general use, suitable for web browsing and light streaming.",
			"Unlimited free calls to two pre-registered numbers of your choice (friends or family).",
			"500 minutes of local calls to other networks, with a competitive call rate for additional minutes."
	};
	private float weeklyPackagePrice = 550.0f;
	private float monthlyPackagePrice = 450.0f;
	private int currentlyActiveDuration;
	private boolean isUpgradeRequested;
	private boolean isDeactivationRequested;
	
	private UpaharaService() {}
	
	public static UpaharaService getInstance() {
		if (instance == null)
			instance = new UpaharaService();
			
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

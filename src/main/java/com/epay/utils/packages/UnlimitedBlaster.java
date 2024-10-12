package com.epay.utils.packages;

public class UnlimitedBlaster implements IPackage {
	private static UnlimitedBlaster instance;
	
	private String packageName = "Unlimited Blaster";
	private String[] packageDetails = {
			"Unlimited calls to any local number with no additional charges.",
			"Unlimited high-speed data with a fair usage policy (speed is reduced after 100 GB).",
			"Unlimited text messages to all local networks."
	};
	private float packagePrice = 50.0f;
	private float weeklyPackagePrice = 250.0f;
	private float monthlyPackagePrice = 200.0f;
	private String currentlyActiveDuration;
	private boolean isUpgradeRequested;
	private boolean isDeactivationRequested;
	
	private UnlimitedBlaster() {}
	
	public static UnlimitedBlaster getInstance() {
		if (instance == null)
			instance = new UnlimitedBlaster();
			
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
	
	public String getCurrentlyActiveDuration() {
		return currentlyActiveDuration;
	}

	public boolean isUpgradeRequested() {
		return isUpgradeRequested;
	}

	public boolean isDeactivationRequested() {
		return isDeactivationRequested;
	}

	public void setWeeklyPackagePrice(float weeklyPackagePrice) {
		this.weeklyPackagePrice = weeklyPackagePrice;
	}

	public void setMonthlyPackagePrice(float monthlyPackagePrice) {
		this.monthlyPackagePrice = monthlyPackagePrice;
	}

	public void setCurrentlyActiveDuration(String currentlyActiveDuration) {
		this.currentlyActiveDuration = currentlyActiveDuration;
	}

	public void setUpgradeRequested(boolean isUpgradeRequested) {
		this.isUpgradeRequested = isUpgradeRequested;
	}

	public void setDeactivationRequested(boolean isDeactivationRequested) {
		this.isDeactivationRequested = isDeactivationRequested;
	}
}

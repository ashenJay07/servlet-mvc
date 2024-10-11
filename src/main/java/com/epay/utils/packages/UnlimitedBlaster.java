package com.epay.utils.packages;

public class UnlimitedBlaster {
	private static UnlimitedBlaster instance;
	
	private String packageName = "Unlimited Blaster";
	private String[] packageDetails = {
			"Unlimited calls to any local number with no additional charges.",
			"Unlimited high-speed data with a fair usage policy (speed is reduced after 100 GB).",
			"Unlimited text messages to all local networks."
	};
	private float packagePrice = 50.0f;
	
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
}

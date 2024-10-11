package com.epay.utils.packages;

public class FunBlaster {
	private static FunBlaster instance;
	
	private String packageName = "Fun Blaster";
	private String[] packageDetails = {
			"10 GB of high-speed data for browsing, streaming, and gaming.",
			"1000 minutes of voice calls to local and international numbers (fair usage applies).",
			"Unlimited access to social media platforms (Facebook, WhatsApp, Instagram) without consuming data."
	};
	private float packagePrice = 45.0f;
	
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
}

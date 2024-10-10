package com.itpproject.model;

public class Package {
	private int id;
	private String packageName;
	private float packagePrice;
	
	public Package(String packageName) {
		this.packageName = packageName;
	}
	
	public Package(int id, String packageName, float packagePrice) {
		this.id = id;
		this.packageName = packageName;
		this.packagePrice = packagePrice;
	} 
	
	public String getPackageName() {
		return packageName;
	}
	
	public void printPackage() {
		System.out.println(id + " -- " + packageName + " -- " + packagePrice);
	}
}

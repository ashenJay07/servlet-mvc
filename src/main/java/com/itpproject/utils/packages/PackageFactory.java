package com.itpproject.utils.packages;

public class PackageFactory {
	public Package getPackageInfo(String packageName) {
		if (packageName == null)
			throw new IllegalArgumentException();
		
		if (packageName.equalsIgnoreCase("Unlimited Blaster"))
			return UnlimitedBlaster.getInstance();
		else if(packageName.equalsIgnoreCase("Unlimited Blaster"))
			return FunBlaster.getInstance();
		
		return null;
	}
}

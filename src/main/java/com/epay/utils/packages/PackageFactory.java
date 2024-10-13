package com.epay.utils.packages;

public class PackageFactory {
	private static IPackage instance = null;

	public static IPackage getPackageInstance(String packageType) {
		if (packageType.equalsIgnoreCase("Fun Blaster")) {
			instance = FunBlaster.getInstance();
		}
		else if (packageType.equalsIgnoreCase("Unlimited Blaster")) {
			instance = UnlimitedBlaster.getInstance();
		}
		else if (packageType.equalsIgnoreCase("Roaming")) {
			instance = Roaming.getInstance();
		}
		else if (packageType.equalsIgnoreCase("E-Doc Service")) {
			instance = EDoc.getInstance();
		}
		else if (packageType.equalsIgnoreCase("Upahara Service")) {
			instance = UpaharaService.getInstance();
		}
		return instance;
	}
}

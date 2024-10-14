package com.epay.model.users;

public class Admin {
    private static Admin instance;
    
    private String adminUID;
    private String adminName;
    private String adminEmail;
    private String adminPassword;

    private Admin() {
        this.adminUID = "AUID0001";
        this.adminName = "David Smith";
        this.adminEmail = "admin@gmail.com";
        this.adminPassword = "asd123";
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public void setLoginCredentials(String adminUID, String adminName, String adminEmail, String adminPassword) {
    	this.adminUID = adminUID;
    	this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public boolean validate(String adminEmail, String adminPassword) {
    	return this.adminEmail.equalsIgnoreCase(adminEmail) && this.adminPassword.equals(adminPassword);
    }
    
    public String getAdminId() {
        return adminUID;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }
}
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

    public void setLoginCredentials(String adminEmail, String adminPassword) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public boolean login(String adminEmail, String adminPassword) {
    	if (this.adminEmail.equalsIgnoreCase(adminEmail) && this.adminPassword.equals(adminPassword)) {
            return true;
        }
        return false;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }
}
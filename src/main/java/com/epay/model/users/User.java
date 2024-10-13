package com.epay.model.users;

public class User {
	private static User instance;
	
	private String userId;
	private String userName;
	private String userEmail;
	
	private User() {
		userId = "UID0001";
		userName = "John Doe";
		userEmail = "johndoe@yahoo.com";
	}
	
	public static User getInstance() {
		if (instance == null)
			instance = new User();
		
		return instance;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}
}
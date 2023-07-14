package com.expenseTracking.model;

public class UserDTO {
	String username;
	String password;
	
	public UserDTO() {
		username = "";
		password = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

package com.cognizant.RestoraApp.util;

import jakarta.validation.constraints.NotBlank;

public class LoginAdmin {

	private final static String ADMINNAME = "Restora Admin";
	private final static String ADMINUSER = "restora@admin";
	private final static String ADMINPASS = "restora@admin";

	public static String getADMINNAME() {
		return ADMINNAME;
	}

	public static String getADMINUSER() {
		return ADMINUSER;
	}

	public static String getADMINPASS() {
		return ADMINPASS;
	}

	@NotBlank(message = "Enter Admin Username")
	private String username;
	@NotBlank(message = "Enter Admin Password")
	private String password;

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

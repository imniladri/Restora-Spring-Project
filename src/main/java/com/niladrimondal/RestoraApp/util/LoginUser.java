package com.niladrimondal.RestoraApp.util;

import jakarta.validation.constraints.NotBlank;

public class LoginUser {

	@NotBlank(message = "Enter your username")
	private String username;
	@NotBlank(message = "Enter your password")
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

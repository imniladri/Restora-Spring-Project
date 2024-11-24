package com.niladrimondal.RestoraApp.service;

import org.springframework.stereotype.Service;

import com.niladrimondal.RestoraApp.util.LoginAdmin;

@Service
public class AdminService {

	public String validateAdmin(LoginAdmin loginAdmin) {

		String username = loginAdmin.getUsername();
		String password = loginAdmin.getPassword();

		String validAdminUser = LoginAdmin.getADMINUSER();
		String validAdminPass = LoginAdmin.getADMINPASS();

		if (loginAdmin != null && username.equals(validAdminUser)) {
			if (password.equals(validAdminPass)) {
				return "Admin Logged In";
			} else {
				return "Invalid Admin Password";
			}
		} else {
			return "Invalid Admin Username";
		}

	}

}

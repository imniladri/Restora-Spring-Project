package com.niladrimondal.RestoraApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class RootController {

	private static final Logger logger = LoggerFactory.getLogger(RootController.class);

	@GetMapping("/")
	public String showLoginPage(HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;
		boolean isAdminLoggedIn = session.getAttribute("adminSession") != null;

		logger.info("Root Page : isUserLoggedIn: {}", isUserLoggedIn);
		logger.info("Root Page : isAdminLoggedIn: {}", isAdminLoggedIn);

		if (!isUserLoggedIn) {
			session.setAttribute("userLoggedIn", false);
		}
		if (!isAdminLoggedIn) {
			session.setAttribute("adminLoggedIn", false);
		}
		return "index";
	}

}

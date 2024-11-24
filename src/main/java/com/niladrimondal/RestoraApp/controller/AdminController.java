package com.niladrimondal.RestoraApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niladrimondal.RestoraApp.service.AdminService;
import com.niladrimondal.RestoraApp.util.LoginAdmin;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

//	-----------------------------------------------------------
//	Admin Login
//	-----------------------------------------------------------

	@GetMapping("/login")
	public String showAdminLoginPage(@ModelAttribute("loginAdmin") LoginAdmin loginAdmin, HttpSession session) {

		boolean isAdminLoggedIn = session.getAttribute("adminSession") != null;

		if (isAdminLoggedIn) {
			return "redirect:/";
		} else {
			session.invalidate();
			return "loginadmin";
		}

	}

	@PostMapping("/login")
	public String getAdminLogin(@Valid @ModelAttribute("loginAdmin") LoginAdmin loginAdmin, BindingResult result,
			Model model, HttpSession session) {

		String status = adminService.validateAdmin(loginAdmin);

		if (result.hasErrors()) {
			return "loginadmin";
		} else if (status.equals("Admin Logged In")) {
			session.setAttribute("adminSession", loginAdmin);
			session.setAttribute("adminLoggedIn", true);
			session.setAttribute("pageLoaded", true);
			return "redirect:/";
		} else {
			model.addAttribute("errorMsg", status);
			return "loginadmin";
		}
	}

//	-----------------------------------------------------------
//	Admin Logout
//	-----------------------------------------------------------

	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		boolean isAdminLoggedIn = session.getAttribute("adminSession") != null;
		if (isAdminLoggedIn) {
			session.invalidate();
			return "redirect:/admin/login";
		} else {
			return "redirect:/";
		}
	}

}

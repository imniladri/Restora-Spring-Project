package com.niladrimondal.RestoraApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niladrimondal.RestoraApp.bean.UserBean;
import com.niladrimondal.RestoraApp.entity.User;
import com.niladrimondal.RestoraApp.service.UserService;
import com.niladrimondal.RestoraApp.util.LoginUser;
import com.niladrimondal.RestoraApp.util.ResetPassword;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	private UserService userService;

	private ModelMapper modelMapper;

	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper) {
		super();
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@InitBinder()
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

//	-----------------------------------------------------------
//	User Login
//	-----------------------------------------------------------

	@GetMapping("/login")
	public String showLoginPage(@ModelAttribute("login") LoginUser login, HttpSession session, Model model) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;

		if (isUserLoggedIn) {
			return "redirect:/";
		} else {
			session.invalidate();
			return "login";
		}
	}

	@PostMapping("/login")
	public String getUserLogin(@Valid @ModelAttribute("login") LoginUser login, BindingResult result, Model model,
			HttpSession session) {
		String status = userService.validateLogin(login);

		if (result.hasErrors()) {
			return "login";
		} else if (status.equals("Logged In")) {
			User user = userService.getUser(login.getUsername());
			UserBean userBean = modelMapper.map(user, UserBean.class);
			session.setAttribute("userSession", userBean);
			session.setAttribute("userLoggedIn", true);
			session.setAttribute("pageLoaded", true);
			return "redirect:/";
		} else {
			model.addAttribute("errorMsg", status);
			return "login";
		}
	}

//	-----------------------------------------------------------
//	User Register
//	-----------------------------------------------------------

	@GetMapping("/register")
	public String showRegisterPage(@ModelAttribute("user") UserBean userBean, HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;

		if (isUserLoggedIn) {
			return "redirect:/";
		} else {
			return "register";
		}
	}

	@PostMapping("/register")
	public String getUserRegister(@Valid @ModelAttribute("user") UserBean userBean, BindingResult result,
			Model modelReg) {

		User user = modelMapper.map(userBean, User.class);

		if (result.hasErrors()) {
			return "register";
		} else if (userService.validateUser(user) && userService.validateEmail(user)) {
			String status = userService.registerUser(user);
			modelReg.addAttribute("successHead", "Registered Successfully!");
			modelReg.addAttribute("successPara", "You gave successfully registered in Restora! Kindly log in back.");
			modelReg.addAttribute("successBtn", "Back to Login");
			modelReg.addAttribute("successUrl", "/login");
			return status;
		} else {
			if (!userService.validateUser(user)) {
				modelReg.addAttribute("errorMsg", "Username already exists.");
			}
			if (!userService.validateEmail(user)) {
				modelReg.addAttribute("errorMsg", "Email already exists.");
			}
			return "register";
		}
	}

//	-----------------------------------------------------------
//	User Reset
//	-----------------------------------------------------------

	@GetMapping("/reset")
	public String showResetPasswordPage(@ModelAttribute("reset") ResetPassword reset, HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;

		if (isUserLoggedIn) {
			return "redirect:/";
		} else {
			return "reset";
		}
	}

	@PostMapping("/reset")
	public String getUserResetPassword(@Valid @ModelAttribute("reset") ResetPassword reset, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		String status = userService.validatePassReset(reset);

		if (result.hasErrors()) {
			return "reset";
		} else if (status.equals("Password Reset Done")) {
			redirectAttributes.addFlashAttribute("passStatus", "Password Changed Successfully!");
			return "redirect:/login";
		} else {
			model.addAttribute("errorMsg", status);
			return "reset";
		}
	}

//	-----------------------------------------------------------
//	User Logout
//	-----------------------------------------------------------

	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;
		if (isUserLoggedIn) {
			session.invalidate();
//			session.removeAttribute("pageLoaded");
//			session.removeAttribute("userSession");
//			session.removeAttribute("cart");
			return "redirect:/login";
		} else {
			return "redirect:/";
		}
	}

//	-----------------------------------------------------------
//	User Dashboard
//	-----------------------------------------------------------

	@GetMapping("/dashboard/{username}")
	public String showUserDashboardPage(HttpSession session, Model model, @PathVariable("username") String username) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;
		if (isUserLoggedIn) {
			User user = userService.getUser(username);
			UserBean userBean = modelMapper.map(user, UserBean.class);
			String dob = userBean.getDob().toString();
			model.addAttribute("command", userBean);
			model.addAttribute("dobFormat", dob);
			return "dashboard";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("/dashboard/updateUser")
	public String updateProfile(@ModelAttribute("user") UserBean userBean) {
		User user = modelMapper.map(userBean, User.class);
		String username = user.getUsername();
		userService.updateUser(user);
		return "redirect:/dashboard/" + username;
	}

	@GetMapping("/dashboard/deleteUser/{username}")
	public String deleteUserProfile(@PathVariable("username") String username, HttpSession session) {
		User user = userService.getUser(username);
		boolean isUserDeleted = userService.deleteUser(user.getUserId());
		if (isUserDeleted) {
			session.removeAttribute("userSession");
			return "redirect:/";
		} else {
			return "redirect:/dashboard/" + username;
		}
	}

}

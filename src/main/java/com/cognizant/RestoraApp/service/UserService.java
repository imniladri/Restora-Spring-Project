package com.cognizant.RestoraApp.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.RestoraApp.dao.UserRepository;
import com.cognizant.RestoraApp.entity.User;
import com.cognizant.RestoraApp.util.LoginUser;
import com.cognizant.RestoraApp.util.ResetPassword;

@Service
public class UserService {

	private UserRepository userRepository;	

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public String registerUser(User user) {

		// Password Hashing
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPassword);

		User userObj = userRepository.save(user);
		if (userObj != null) {
			return "success";
		} else {
			return "error";
		}
	}

	public boolean updateUser(User user) {
		User editUser = userRepository.save(user);
		if (editUser != null) {
			return true;
		}
		return false;
	}

	public boolean deleteUser(Integer userId) {
		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}

	public User getUser(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	public User getUserById(Integer userId) {
		User user = userRepository.findById(userId).get();
		return user;
	}

	public boolean validateUser(User user) {
		String enteredUsername = user.getUsername();

		User existsUser = userRepository.findByUsername(enteredUsername);

		if (existsUser == null) {
			return true;
		}
		return false;
	}

	public boolean validateEmail(User user) {
		String enteredEmail = user.getEmail();

		User existsUserEmail = userRepository.findByEmail(enteredEmail);

		if (existsUserEmail == null) {
			return true;
		}
		return false;
	}

	public String validateLogin(LoginUser login) {
		String loginUserName = login.getUsername();
		String loginUserPass = login.getPassword();

		User user = userRepository.findByUsername(loginUserName);

		if (user != null) {
//			Authenticate Without Password Hashing: "user.getPassword().equals(loginUserPass)"

			if (BCrypt.checkpw(loginUserPass, user.getPassword())) {
				return "Logged In";
			} else {
				return "Wrong Password!";
			}
		} else {
			return "Username Doesn't Exists!";
		}
	}

	public String validatePassReset(ResetPassword reset) {
		String resetUserName = reset.getUsername();
		String resetUserNewPass = reset.getPassword();
		String resetUserNewPassConfirm = reset.getNewpassword();

		User user = userRepository.findByUsername(resetUserName);

		if (user != null) {
			if (resetUserNewPass.equals(resetUserNewPassConfirm)) {
				String hashedPassword = BCrypt.hashpw(resetUserNewPassConfirm, BCrypt.gensalt());
				user.setPassword(hashedPassword);
				userRepository.save(user);
				return "Password Reset Done";
			} else {
				return "Password doesn't match";
			}
		} else {
			return "Invalid Username";
		}

	}

}

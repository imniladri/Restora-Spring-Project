package com.niladrimondal.RestoraApp.bean;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserBean {

	private Integer userId;

	@NotEmpty(message = "{user.username.empty}")
	@Size(min = 8, max = 20, message = "{user.username.invalid}")
	private String username;

	@NotEmpty(message = "{user.password.empty}")
	@Size(min = 8, max = 20, message = "{user.password.invalid}")
	private String password;

	@NotEmpty(message = "{user.fullName.empty}")
	private String fullName;

	@NotEmpty(message = "{user.email.empty}")
	@Email(message = "{user.email.invalid}")
	private String email;

	@NotNull(message = "{user.dob.empty}")
	@Past(message = "{user.dob.invalid}")
	private LocalDate dob;

	@NotNull(message = "{user.gender.empty}")
	private String gender;

	@NotEmpty(message = "{user.mobileNo.empty}")
	@Size(min = 10, max = 10, message = "{user.mobileNo.invalid}")
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "{user.mobileNo.invalid}")
	private String mobileNo;

	@NotEmpty(message = "{user.address1.empty}")
	private String address1;

	private String address2;

	public UserBean() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", username=" + username + ", password=" + password + ", fullName="
				+ fullName + ", email=" + email + ", dob=" + dob + ", gender=" + gender + ", mobileNo=" + mobileNo
				+ ", address1=" + address1 + ", address2=" + address2 + "]";
	}

}

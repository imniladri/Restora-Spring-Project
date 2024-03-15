package com.cognizant.RestoraApp.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "RESTORA_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GEN")
	@SequenceGenerator(name = "USER_ID_GEN", sequenceName = "USER_ID_SEQ", allocationSize = 1,initialValue = 100)
	@Column(name = "USERID")
	private Integer userId;

	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "FULLNAME", nullable = false)
	private String fullName;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name = "DOB", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate dob;

	@Column(name = "GENDER", nullable = false)
	private String gender;

	@Column(name = "MOBILENO", nullable = false)
	private String mobileNo;

	@Column(name = "ADDRESS1", nullable = false)
	private String address1;

	@Column(name = "ADDRESS2")
	private String address2;

	public User() {
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
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", fullName=" + fullName
				+ ", email=" + email + ", dob=" + dob + ", gender=" + gender + ", mobileNo=" + mobileNo + ", address1="
				+ address1 + ", address2=" + address2 + "]";
	}

	// Mapping (User-Order)

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Order> orderList;

	public Set<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(Set<Order> orderList) {
		this.orderList = orderList;
	}

	public void addToOrderList(Order order) {
		if (order != null) {
			if (orderList == null) {
				orderList = new HashSet<>();
			}
			order.setUser(this);
			orderList.add(order);
		}
	}

	// Mapping (User-Booking)

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Booking> bookingList;

	public Set<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(Set<Booking> bookingList) {
		this.bookingList = bookingList;
	}

	public void addToBookingList(Booking booking) {
		if (booking != null) {
			if (bookingList == null) {
				bookingList = new HashSet<>();
			}
			booking.setUser(this);
			bookingList.add(booking);
		}
	}

}

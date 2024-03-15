package com.cognizant.RestoraApp.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "RESTORA_BOOKING")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKING_ID_GEN")
	@SequenceGenerator(name = "BOOKING_ID_GEN", sequenceName = "BOOKING_ID_SEQ", allocationSize = 1, initialValue = 100)
	@Column(name = "BOOKING_ID")
	private Integer bookingId;

	@Column(name = "BOOKING_DATE")
	@Temporal(TemporalType.DATE)
	private LocalDate bookingDate;

	@Column(name = "BOOKING_TIME_FROM")
	@Temporal(TemporalType.TIME)
	private LocalTime bookingTimeFrom;

	@Column(name = "BOOKING_TIME_TO")
	@Temporal(TemporalType.TIME)
	private LocalTime bookingTimeTo;

	@Column(name = "BOOKING_PURPOSE")
	private String bookingPurpose;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "CUSTOMER_EMAIL")
	private String customerEmail;

	@Column(name = "CUSTOMER_PHONENO")
	private String customerPhoneNo;

	@Column(name = "NO_OF_PERSONS")
	private Integer noOfPersons;

	public Booking() {
		super();
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalTime getBookingTimeFrom() {
		return bookingTimeFrom;
	}

	public void setBookingTimeFrom(LocalTime bookingTimeFrom) {
		this.bookingTimeFrom = bookingTimeFrom;
	}

	public LocalTime getBookingTimeTo() {
		return bookingTimeTo;
	}

	public void setBookingTimeTo(LocalTime bookingTimeTo) {
		this.bookingTimeTo = bookingTimeTo;
	}

	public String getBookingPurpose() {
		return bookingPurpose;
	}

	public void setBookingPurpose(String bookingPurpose) {
		this.bookingPurpose = bookingPurpose;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}

	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}

	public Integer getNoOfPersons() {
		return noOfPersons;
	}

	public void setNoOfPersons(Integer noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", bookingTimeFrom="
				+ bookingTimeFrom + ", bookingTimeTo=" + bookingTimeTo + ", bookingPurpose=" + bookingPurpose
				+ ", customerName=" + customerName + ", customerEmail=" + customerEmail + ", customerPhoneNo="
				+ customerPhoneNo + ", noOfPersons=" + noOfPersons + "]";
	}

	// Mapping (User-Booking)

	@ManyToOne
	@JoinColumn(name = "USERID")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

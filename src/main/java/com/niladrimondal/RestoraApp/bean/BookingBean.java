package com.niladrimondal.RestoraApp.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BookingBean {

	private Integer bookingId;

	@NotNull(message = "{booking.bookingDate.empty}")
	@Future(message = "{booking.bookingDate.invalid}")
	private LocalDate bookingDate;

	@NotNull(message = "{booking.bookingTimeFrom.empty}")
	private LocalTime bookingTimeFrom;

	@NotNull(message = "{booking.bookingTimeTo.empty}")
	private LocalTime bookingTimeTo;

	@NotEmpty(message = "{booking.bookingPurpose.empty}")
	private String bookingPurpose;

	@NotEmpty(message = "{booking.customerName.empty}")
	private String customerName;

	@NotEmpty(message = "{booking.customerEmail.empty}")
	@Email(message = "{booking.customerEmail.invalid}")
	private String customerEmail;

	@NotEmpty(message = "{booking.customerPhoneNo.empty}")
	@Size(min = 10, max = 10, message = "{booking.customerPhoneNo.invalid}")
	@Pattern(regexp = "^[0-9]{10}$", message = "{booking.customerPhoneNo.invalid}")
	private String customerPhoneNo;

	@NotNull(message = "{booking.noOfPersons.empty}")
	@Min(value = 1, message = "{booking.noOfPersons.invalidMin}")
	@Max(value = 10, message = "{booking.noOfPersons.invalidMax}")
	private Integer noOfPersons;

	public BookingBean() {
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
		return "BookingBean [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", bookingTimeFrom="
				+ bookingTimeFrom + ", bookingTimeTo=" + bookingTimeTo + ", bookingPurpose=" + bookingPurpose
				+ ", customerName=" + customerName + ", customerEmail=" + customerEmail + ", customerPhoneNo="
				+ customerPhoneNo + ", noOfPersons=" + noOfPersons + "]";
	}

}

package com.cognizant.RestoraApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import com.cognizant.RestoraApp.bean.BookingBean;
import com.cognizant.RestoraApp.bean.UserBean;
import com.cognizant.RestoraApp.entity.Booking;
import com.cognizant.RestoraApp.entity.User;
import com.cognizant.RestoraApp.exceptions.BookingsNotFoundException;
import com.cognizant.RestoraApp.service.BookingService;
import com.cognizant.RestoraApp.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookingController {

	private UserService userService;

	private BookingService bookingService;

	private ModelMapper modelMapper;

	@Autowired
	public BookingController(UserService userService, BookingService bookingService, ModelMapper modelMapper) {
		super();
		this.userService = userService;
		this.bookingService = bookingService;
		this.modelMapper = modelMapper;
	}

	@InitBinder()
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

//	-----------------------------------------------------------
//	Show All Bookings
//	-----------------------------------------------------------

	@GetMapping("/bookings")
	public String showAllBookings(Model model, HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;
		boolean isAdminLoggedIn = session.getAttribute("adminSession") != null;

		if (isUserLoggedIn) {
			session.getAttribute("userLoggedIn");

			UserBean userBean = (UserBean) session.getAttribute("userSession");
			User user = modelMapper.map(userBean, User.class);

			List<Booking> isBookingListAvailable = bookingService.getAllBookingsByUser(user.getUserId());

			if (isBookingListAvailable == null) {
				throw new BookingsNotFoundException();
			} else {
				List<BookingBean> bookingList = isBookingListAvailable.stream()
						.map(booking -> modelMapper.map(booking, BookingBean.class)).collect(Collectors.toList());
				model.addAttribute("bookings", bookingList);
			}

		} else if (isAdminLoggedIn) {
			session.getAttribute("adminLoggedIn");

			List<Booking> isBookingListAvailable = bookingService.getAllBookings();

			if (isBookingListAvailable == null) {
				throw new BookingsNotFoundException();
			} else {
				List<BookingBean> bookingList = isBookingListAvailable.stream()
						.map(booking -> modelMapper.map(booking, BookingBean.class)).collect(Collectors.toList());
				model.addAttribute("bookings", bookingList);
			}

		} else {
			return "redirect:/login";
		}
		return "bookings";
	}

//	-----------------------------------------------------------
//	Reservation Table (Booking)
//	-----------------------------------------------------------

	@GetMapping("/reservation")
	public String showReservationPage(@ModelAttribute("booking") BookingBean bookingBean, HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;

		if (isUserLoggedIn) {
			return "reservation";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("/reservation")
	public String getUserReservationDone(@Valid @ModelAttribute("booking") BookingBean bookingBean,
			BindingResult result, Model model, HttpSession session) {

		UserBean userBean = (UserBean) session.getAttribute("userSession");
		Booking booking = modelMapper.map(bookingBean, Booking.class);
		User user = modelMapper.map(userBean, User.class);
		user.addToBookingList(booking);

		if (result.hasErrors()) {
			return "reservation";
		} else if (userService.updateUser(user)) {
			if (bookingService.addBooking(booking)) {
				model.addAttribute("successHead", "Booked Table Successfully!");
				model.addAttribute("successPara", "Your table is booked! Kindly check all your bookings.");
				model.addAttribute("successBtn", "View Bookings");
				model.addAttribute("successUrl", "/bookings");
				return "success";
			} else {
				return "error";
			}
		} else {
			model.addAttribute("failedMsg", "Booking Not Completed!");
			return "reservation";
		}

	}

//	-----------------------------------------------------------
//	Delete Reservation/Booking
//	-----------------------------------------------------------

	@GetMapping("/bookings/deleteBooking/{bookingId}")
	public String deleteBooking(@PathVariable("bookingId") Integer bookingId, RedirectAttributes redirectAttributes) {

		boolean isBookingDeleted = bookingService.deleteBooking(bookingId);

		if (isBookingDeleted) {
			redirectAttributes.addFlashAttribute("deleteSuccess", "Booking Removed Successfully!");
		} else {			
			redirectAttributes.addFlashAttribute("deleteError", "Can't Delete Booking");
		}

		return "redirect:/bookings";
	}

}

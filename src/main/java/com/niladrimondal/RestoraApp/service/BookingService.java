package com.niladrimondal.RestoraApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niladrimondal.RestoraApp.dao.BookingRepository;
import com.niladrimondal.RestoraApp.entity.Booking;

@Service
public class BookingService {

	private BookingRepository bookingRepository;

	@Autowired
	public BookingService(BookingRepository bookingRepository) {
		super();
		this.bookingRepository = bookingRepository;
	}

	public boolean addBooking(Booking booking) {
		Booking booked = bookingRepository.save(booking);

		if (booked != null) {
			return true;
		}
		return false;
	}

	public boolean deleteBooking(Integer bookingId) {
		if (bookingRepository.existsById(bookingId)) {
			bookingRepository.deleteById(bookingId);
			return true;
		}
		return false;
	}

	public List<Booking> getAllBookingsByUser(Integer userId) {
		List<Booking> bookingsList = bookingRepository.findAllBookingsByUserId(userId);

		if (!bookingsList.isEmpty()) {
			return bookingsList;
		} else {
			return null;
		}
	}

	public List<Booking> getAllBookings() {
		List<Booking> bookingsList = bookingRepository.findAll();

		if (!bookingsList.isEmpty()) {
			return bookingsList;
		} else {
			return null;
		}
	}

}

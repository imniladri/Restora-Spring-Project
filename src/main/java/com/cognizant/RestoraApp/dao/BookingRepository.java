package com.cognizant.RestoraApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.RestoraApp.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	@Query("SELECT booking FROM Booking booking WHERE booking.user.userId = :userId")
	List<Booking> findAllBookingsByUserId(@Param("userId") Integer userId);

}

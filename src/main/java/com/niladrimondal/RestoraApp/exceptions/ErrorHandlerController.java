package com.niladrimondal.RestoraApp.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

//	-----------------------------------------------------------
//	All Errors
//	-----------------------------------------------------------

	@ExceptionHandler(value = Exception.class)
	public String showError(Exception ex, Model model) {

		String errorExceptionMsg = ex.getMessage();
		String errorExceptionName = ex.getClass().getSimpleName();

		model.addAttribute("errorHead", "Something Went Wrong!");
		model.addAttribute("errorPara", "An error occured. Please check the console for more details.");
		model.addAttribute("errorExceptionMsg", errorExceptionMsg);
		model.addAttribute("errorExceptionName", errorExceptionName);

		return "error";
	}

//	-----------------------------------------------------------
//	Bookings Error
//	-----------------------------------------------------------

	@ExceptionHandler(value = BookingsNotFoundException.class)
	public String showBookingsError(Exception ex, Model model) {
		
		model.addAttribute("isBookingListNA", true);

		return "bookings";
	}

//	-----------------------------------------------------------
//	Items Error
//	-----------------------------------------------------------

	@ExceptionHandler(value = ItemsNotFoundException.class)
	public String showItemsError(Model model) {

		model.addAttribute("isFoodItemListNA", true);

		return "menu";
	}

//	-----------------------------------------------------------
//	Orders Error
//	-----------------------------------------------------------

	@ExceptionHandler(value = OrdersNotFoundException.class)
	public String showOrdersError(Model model) {

		model.addAttribute("isOrdersListNA", true);

		return "orders";
	}

}

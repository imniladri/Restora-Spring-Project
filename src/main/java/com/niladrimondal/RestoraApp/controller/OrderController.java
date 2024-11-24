package com.niladrimondal.RestoraApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import com.niladrimondal.RestoraApp.bean.OrderBean;
import com.niladrimondal.RestoraApp.bean.UserBean;
import com.niladrimondal.RestoraApp.entity.Order;
import com.niladrimondal.RestoraApp.entity.User;
import com.niladrimondal.RestoraApp.exceptions.OrdersNotFoundException;
import com.niladrimondal.RestoraApp.service.CartService;
import com.niladrimondal.RestoraApp.service.OrderService;
import com.niladrimondal.RestoraApp.util.CartItem;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	private OrderService orderService;

	private ModelMapper modelMapper;

	@Autowired
	public OrderController(OrderService orderService, ModelMapper modelMapper) {
		super();
		this.orderService = orderService;
		this.modelMapper = modelMapper;
	}

	@InitBinder()
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

//	-----------------------------------------------------------
//	Show Payment Success
//	-----------------------------------------------------------

	@GetMapping("/payment")
	public String showPaymentPage(HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;

		if (isUserLoggedIn) {
			return "payment";
		} else {
			return "redirect:/login";
		}
	}

//	-----------------------------------------------------------
//	Place New Order
//	-----------------------------------------------------------

	@GetMapping("/order")
	public String placeOrder(HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;

		if (isUserLoggedIn) {

			// Get User
			UserBean userBean = (UserBean) session.getAttribute("userSession");
			User user = modelMapper.map(userBean, User.class);

			// Get Cart List
			CartService cartService = (CartService) session.getAttribute("cart");
			Set<CartItem> cart = cartService.getCartItems();

			// Get Order
			OrderBean orderBean = new OrderBean();
			Order order = modelMapper.map(orderBean, Order.class);

			boolean isOrderPlaced = orderService.placeOrder(user, cart, order, session);

			if (isOrderPlaced) {
				session.removeAttribute("cart");
				return "redirect:/payment";
			}
			return "redirect:/cart";

		} else {
			return "redirect:/login";
		}

	}

//	-----------------------------------------------------------
//	View All Orders
//	-----------------------------------------------------------

	@GetMapping("/orders")
	public String viewAllOrders(Model model, HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;

		if (isUserLoggedIn) {

			UserBean userBean = (UserBean) session.getAttribute("userSession");
			User user = modelMapper.map(userBean, User.class);

			List<Order> ordersList = orderService.getAllOrders(user.getUserId());

			if (ordersList == null) {
				throw new OrdersNotFoundException();
			} else {
				model.addAttribute("ordersList", ordersList);
			}

			return "orders";

		} else {
			session.setAttribute("userLoggedIn", false);
			return "redirect:/login";
		}
	}

}

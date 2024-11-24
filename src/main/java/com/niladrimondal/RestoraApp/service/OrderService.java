package com.niladrimondal.RestoraApp.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niladrimondal.RestoraApp.dao.OrderRepository;
import com.niladrimondal.RestoraApp.entity.Item;
import com.niladrimondal.RestoraApp.entity.Order;
import com.niladrimondal.RestoraApp.entity.User;
import com.niladrimondal.RestoraApp.util.CartItem;

import jakarta.servlet.http.HttpSession;

@Service
public class OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	public boolean placeOrder(User user, Set<CartItem> cart, Order order, HttpSession session) {

		CartService cartService = (CartService) session.getAttribute("cart");
		Double totalPrice = cartService.getTotalPrice(cart);

		order.setOrderDate(LocalDate.now());
		order.setOrderTotal(totalPrice);
		order.setOrderPayment("Payment Successful");
		order.setOrderItemCount(cart.size());

		Set<Item> items = new HashSet<>();
		for (CartItem cartItem : cart) {
			Item item = cartItem.getItem();
			items.add(item);
		}

		order.setItem(items);

		user.addToOrderList(order);

		Order orderPlaced = orderRepository.save(order);

		if (orderPlaced != null) {
			return true;
		}
		return false;

	}

	public List<Order> getAllOrders(Integer userId) {
		List<Order> orderList = orderRepository.findAllOrdersByUserId(userId);
		
		if (orderList.size() > 0) {
			return orderList;
		} else {
			return null;
		}
	}

}

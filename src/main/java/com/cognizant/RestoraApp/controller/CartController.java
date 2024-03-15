package com.cognizant.RestoraApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cognizant.RestoraApp.entity.Item;
import com.cognizant.RestoraApp.service.CartService;
import com.cognizant.RestoraApp.service.ItemService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	private ItemService itemService;

	@Autowired
	public CartController(ItemService itemService) {
		super();
		this.itemService = itemService;
	}

	public CartService getCartSession(HttpSession session) {
		CartService cartService = (CartService) session.getAttribute("cart");
		if (cartService == null) {
			cartService = new CartService();
			session.setAttribute("cart", cartService);
		}
		return cartService;
	}

//	-----------------------------------------------------------
//	Show Cart Page
//	-----------------------------------------------------------

	@GetMapping("/cart")
	public String showCartPage(HttpSession session, Model model) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;

		if (isUserLoggedIn) {

			CartService cartService = this.getCartSession(session);
			model.addAttribute("cartItemList", cartService.getCartItems());
			model.addAttribute("cartTotalPrice", cartService.getTotalPrice(cartService.getCartItems()));

			return "cart";
		} else {
			return "redirect:/login";
		}
	}

//	-----------------------------------------------------------
//	Add to Cart
//	-----------------------------------------------------------

	@GetMapping("/addCart/{foodItemId}")
	public String addToCart(@PathVariable("foodItemId") Integer foodItemId, HttpSession session,
			RedirectAttributes redirectAttributes) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;

		if (isUserLoggedIn) {

			CartService cartService = this.getCartSession(session);
			Item item = itemService.getItem(foodItemId);
			cartService.addItem(item, 1);
			redirectAttributes.addFlashAttribute("isAdded", "active");

			return "redirect:/menu";

		} else {
			return "redirect:/login";
		}
	}

//	-----------------------------------------------------------
//	Remove From Cart
//	-----------------------------------------------------------

	@GetMapping("/removeCart/{foodItemId}")
	public String removeFromCart(@PathVariable("foodItemId") Integer foodItemId, HttpSession session) {

		CartService cartService = this.getCartSession(session);
		Item item = itemService.getItem(foodItemId);
		cartService.removeItem(item);

		return "redirect:/cart";
	}

//	-----------------------------------------------------------
//	Update In Cart
//	-----------------------------------------------------------

	@GetMapping("/updateCartMore/{foodItemId}")
	public String updateCartQuantityIncrease(@PathVariable("foodItemId") Integer foodItemId, HttpSession session,
			RedirectAttributes redirectAttributes) {

		CartService cartService = this.getCartSession(session);
		Item item = itemService.getItem(foodItemId);
		cartService.updateQuantityIncrease(item, 1);

		return "redirect:/cart";
	}

	@GetMapping("/updateCartLess/{foodItemId}")
	public String updateCartQuantityDecrease(@PathVariable("foodItemId") Integer foodItemId, HttpSession session,
			RedirectAttributes redirectAttributes) {

		CartService cartService = this.getCartSession(session);
		Item item = itemService.getItem(foodItemId);
		cartService.updateQuantityDecrease(item, 1);

		return "redirect:/cart";
	}

}

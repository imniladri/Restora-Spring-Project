package com.niladrimondal.RestoraApp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.niladrimondal.RestoraApp.entity.Item;
import com.niladrimondal.RestoraApp.util.CartItem;

@Service
public class CartService {

	private Set<CartItem> cartItems = new HashSet<>();

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalPrice(Set<CartItem> cartItems) {
		double totalPrice = 0;

		for (CartItem cartItem : cartItems) {
			Double itemPrice = cartItem.getItem().getFoodItemPrice();
			int quantity = cartItem.getQuantity();

			totalPrice += itemPrice * quantity;
		}
		return totalPrice;
	}

	public void addItem(Item item, int quantity) {
		CartItem newCartItem = new CartItem(item, quantity);

		if (!cartItems.contains(newCartItem)) {
			cartItems.add(newCartItem);
		} else {
			for (CartItem existsItem : cartItems) {
				if (existsItem.equals(newCartItem)) {
					existsItem.setQuantity(existsItem.getQuantity() + quantity);
					break;
				}
			}
		}
	}

	public void removeItem(Item item) {
		for (CartItem existsItem : cartItems) {
			if (existsItem.getItem().equals(item)) {
				cartItems.remove(existsItem);
				break;
			}
		}
	}

	public void updateQuantityIncrease(Item item, int newQuantity) {
		for (CartItem existsItem : cartItems) {
			if (existsItem.getItem().equals(item)) {
				if (existsItem.getQuantity() < 10) {					
					existsItem.setQuantity(existsItem.getQuantity() + newQuantity);
				}
				break;
			}
		}
	}

	public void updateQuantityDecrease(Item item, int newQuantity) {
		for (CartItem existsItem : cartItems) {
			if (existsItem.getItem().equals(item)) {
				if (existsItem.getQuantity() > 1) {
					existsItem.setQuantity(existsItem.getQuantity() - newQuantity);
				}
				break;
			}
		}
	}

}

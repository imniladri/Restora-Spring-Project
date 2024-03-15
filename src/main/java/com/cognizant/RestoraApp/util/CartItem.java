package com.cognizant.RestoraApp.util;

import com.cognizant.RestoraApp.entity.Item;

public class CartItem {

	private Item item;
	private int quantity;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartItem() {
		super();
	}

	public CartItem(Item item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [item=" + item + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return item.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		return item.equals(other.item);
	}

}

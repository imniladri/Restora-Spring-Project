package com.niladrimondal.RestoraApp.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "RESTORA_ITEM")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_GEN")
	@SequenceGenerator(name = "ITEM_ID_GEN", sequenceName = "ITEM_ID_SEQ", allocationSize = 1, initialValue = 100)
	@Column(name = "FOOD_ITEM_ID")
	private Integer foodItemId;

	@Column(name = "FOOD_ITEM_NAME", nullable = false, unique = true)
	private String foodItemName;

	@Column(name = "FOOD_ITEM_IMGSRC", nullable = false)
	private String foodItemImageSrc;

	@Column(name = "FOOD_ITEM_PRICE", nullable = false)
	private Double foodItemPrice;

	@Column(name = "FOOD_ITEM_CATEGORY", nullable = false)
	private String foodItemCategory;

	@Column(name = "FOOD_ITEM_DESCRIPTION", nullable = false)
	private String foodItemDesc;

	public Item() {
		super();
	}

	public Integer getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(Integer foodItemId) {
		this.foodItemId = foodItemId;
	}

	public String getFoodItemName() {
		return foodItemName;
	}

	public void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}

	public String getFoodItemImageSrc() {
		return foodItemImageSrc;
	}

	public void setFoodItemImageSrc(String foodItemImageSrc) {
		this.foodItemImageSrc = foodItemImageSrc;
	}

	public Double getFoodItemPrice() {
		return foodItemPrice;
	}

	public void setFoodItemPrice(Double foodItemPrice) {
		this.foodItemPrice = foodItemPrice;
	}

	public String getFoodItemCategory() {
		return foodItemCategory;
	}

	public void setFoodItemCategory(String foodItemCategory) {
		this.foodItemCategory = foodItemCategory;
	}

	public String getFoodItemDesc() {
		return foodItemDesc;
	}

	public void setFoodItemDesc(String foodItemDesc) {
		this.foodItemDesc = foodItemDesc;
	}

	@Override
	public String toString() {
		return "Item [foodItemId=" + foodItemId + ", foodItemName=" + foodItemName + ", foodItemImageSrc="
				+ foodItemImageSrc + ", foodItemPrice=" + foodItemPrice + ", foodItemCategory=" + foodItemCategory
				+ ", foodItemDesc=" + foodItemDesc + "]";
	}
	
	@Override
	public int hashCode() {
		return foodItemId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return foodItemId.equals(other.foodItemId);
	}

	// Mapping (Order-Item)

	@ManyToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private Set<Order> order = new HashSet<>();

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

}

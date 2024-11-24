package com.niladrimondal.RestoraApp.bean;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ItemBean {

	private Integer foodItemId;

	@NotEmpty(message = "{item.foodItemName.empty}")
	private String foodItemName;

	@NotEmpty(message = "{item.foodItemImageSrc.empty}")
	private String foodItemImageSrc;

	@NotNull(message = "{item.foodItemPrice.empty}")
	@Min(value = 10, message = "{item.foodItemPrice.invalid}")
	@Max(value = 100, message = "{item.foodItemPrice.invalid}")
	private Double foodItemPrice;

	@NotEmpty(message = "{item.foodItemCategory.empty}")
	private String foodItemCategory;

	@NotEmpty(message = "{item.foodItemDesc.empty}")
	private String foodItemDesc;

	public ItemBean() {
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
		return "ItemBean [foodItemId=" + foodItemId + ", foodItemName=" + foodItemName + ", foodItemImageSrc="
				+ foodItemImageSrc + ", foodItemPrice=" + foodItemPrice + ", foodItemCategory=" + foodItemCategory
				+ ", foodItemDesc=" + foodItemDesc + "]";
	}

}

package com.cognizant.RestoraApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.RestoraApp.dao.ItemRepository;
import com.cognizant.RestoraApp.entity.Item;

@Service
public class ItemService {

	private ItemRepository itemRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}

	public List<Item> getAllItems() {
		List<Item> allItems = itemRepository.findAll();

		if (!allItems.isEmpty()) {
			return allItems;
		} else {
			return null;
		}
	}

	public List<Item> getItemsByCategory(String category) {
		List<Item> allFilterItems = itemRepository.findByFoodItemCategory(category.toLowerCase());

		if (!allFilterItems.isEmpty()) {
			return allFilterItems;
		} else {
			return null;
		}
	}

	public Item getItem(Integer foodItemId) {
		Item item = itemRepository.findById(foodItemId).get();
		return item;
	}

	public boolean addItem(Item item) {
		Item savedItem = itemRepository.save(item);

		if (savedItem != null) {
			return true;
		}
		return false;
	}

	public Item updateItem(Item item) {
		Item updatedItem = itemRepository.save(item);
		return updatedItem;
	}

	public boolean deleteItem(Integer foodItemId) {
		if (itemRepository.existsById(foodItemId)) {
			itemRepository.deleteById(foodItemId);
			return true;
		}
		return false;
	}

	public String validateItem(Item item) {
		Item foodItems = itemRepository.findByFoodItemName(item.getFoodItemName());

		if (foodItems != null) {
			return "Food Item Already Exists";
		} else {
			return "Valid Item";
		}
	}

}

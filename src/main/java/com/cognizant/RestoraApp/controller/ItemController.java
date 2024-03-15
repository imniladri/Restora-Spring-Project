package com.cognizant.RestoraApp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cognizant.RestoraApp.bean.ItemBean;
import com.cognizant.RestoraApp.entity.Item;
import com.cognizant.RestoraApp.exceptions.ItemsNotFoundException;
import com.cognizant.RestoraApp.service.ItemService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ItemController {

	private ItemService itemService;

	private ModelMapper modelMapper;

	@Autowired
	public ItemController(ItemService itemService, ModelMapper modelMapper) {
		super();
		this.itemService = itemService;
		this.modelMapper = modelMapper;
	}

//	-----------------------------------------------------------
//	Show All Menu Item
//	-----------------------------------------------------------

	@GetMapping("/menu")
	public String showMenuPage(ModelMap model, HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;
		boolean isAdminLoggedIn = session.getAttribute("adminSession") != null;

		List<Item> isItemListAvailable = itemService.getAllItems();

//		Session Attribute Check
		if (isUserLoggedIn) {
			session.getAttribute("userLoggedIn");
		} else if (isAdminLoggedIn) {
			session.getAttribute("adminLoggedIn");
		} else {
			session.setAttribute("userLoggedIn", false);
			session.setAttribute("adminLoggedIn", false);
		}
		
//		Menu Item List Check
		if (isItemListAvailable == null) {
			throw new ItemsNotFoundException();
		} else {
			List<ItemBean> foodItemList = isItemListAvailable.stream()
					.map(item -> modelMapper.map(item, ItemBean.class)).collect(Collectors.toList());
			model.addAttribute("foodItems", foodItemList);
		}
		
		return "menu";
	}

//	-----------------------------------------------------------
//	Show Search Menu Item
//	-----------------------------------------------------------

	@GetMapping(value = "/menu", params = "category")
	public String showFilterMenuPage(@RequestParam("category") String category, Model model, HttpSession session) {
		boolean isUserLoggedIn = session.getAttribute("userSession") != null;
		boolean isAdminLoggedIn = session.getAttribute("adminSession") != null;

		if (category.trim().isEmpty()) {
			return "redirect:/menu";
		}

		List<Item> isItemListAvailable = itemService.getItemsByCategory(category);

		if (isItemListAvailable == null) {
			throw new ItemsNotFoundException();
		} else {
			List<ItemBean> foodItemList = isItemListAvailable.stream()
					.map(item -> modelMapper.map(item, ItemBean.class)).collect(Collectors.toList());
			model.addAttribute("foodItems", foodItemList);
		}

		if (isUserLoggedIn) {
			session.getAttribute("userLoggedIn");
		} else if (isAdminLoggedIn) {
			session.getAttribute("adminLoggedIn");
		} else {
			session.setAttribute("userLoggedIn", false);
			session.setAttribute("adminLoggedIn", false);
		}

		return "menu";
	}

//	-----------------------------------------------------------
//	Add New Menu Item
//	-----------------------------------------------------------

	@GetMapping("/newitem")
	public String showAddNewItemPage(@ModelAttribute("item") ItemBean itemBean, HttpSession session) {
		boolean isAdminLoggedIn = session.getAttribute("adminSession") != null;
		if (isAdminLoggedIn) {
			return "newitem";
		} else {
			return "redirect:/admin/login";
		}
	}

	@PostMapping("/newitem")
	public String addNewItemInMenu(@Valid @ModelAttribute("item") ItemBean itemBean, BindingResult result,
			Model model) {

		Item item = modelMapper.map(itemBean, Item.class);
		item.setFoodItemCategory(itemBean.getFoodItemCategory().toLowerCase());
		String status = itemService.validateItem(item);

		if (result.hasErrors()) {
			return "newitem";
		} else if (status.equals("Valid Item")) {
			if (itemService.addItem(item)) {
				model.addAttribute("successHead", "Item Added Successfully!");
				model.addAttribute("successPara", "Your item added to menu. Kindly check all items in menu.");
				model.addAttribute("successBtn", "Go to Menu");
				model.addAttribute("successUrl", "/menu");
				return "success";
			} else {
				return "error";
			}
		} else {
			model.addAttribute("errorMsg", status);
			return "newitem";
		}
	}

//	-----------------------------------------------------------
//	Edit Menu Item
//	-----------------------------------------------------------

	@GetMapping("/editFoodItem/{foodItemId}")
	public String showEditItemPage(@PathVariable("foodItemId") Integer foodItemId, Model model, HttpSession session) {
		boolean isAdminLoggedIn = session.getAttribute("adminSession") != null;

		if (isAdminLoggedIn) {
			Item item = itemService.getItem(foodItemId);
			ItemBean itemBean = modelMapper.map(item, ItemBean.class);
			model.addAttribute("command", itemBean);
			model.addAttribute("deleteFoodItemId", foodItemId);
			return "edititem";
		} else {
			return "redirect:/menu";
		}
	}

	@PostMapping("/editFoodItem/updateItem")
	public String updateItem(@ModelAttribute("item") ItemBean itemBean, Model model) {
		Item item = modelMapper.map(itemBean, Item.class);
		item.setFoodItemCategory(itemBean.getFoodItemCategory().toLowerCase());
		itemService.updateItem(item);
		return "redirect:/menu";
	}

//	-----------------------------------------------------------
//	Delete Menu Item
//	-----------------------------------------------------------

	@GetMapping("/editFoodItem/deleteItem/{foodItemId}")
	public String deleteItem(@PathVariable("foodItemId") Integer foodItemId, RedirectAttributes redirectAttributes) {

		boolean isItemDeleted = itemService.deleteItem(foodItemId);

		if (isItemDeleted) {
			redirectAttributes.addFlashAttribute("deleteSuccess", "Item Removed Successfully!");
		} else {
			redirectAttributes.addFlashAttribute("deleteError", "Can't Delete Item");
		}

		return "redirect:/menu";
	}

}

package com.cognizant.RestoraApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.RestoraApp.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query("SELECT item FROM Item item WHERE foodItemName = :foodItemName")
	Item findByFoodItemName(@Param("foodItemName") String foodItemName);
	
	@Query("SELECT item FROM Item item WHERE foodItemCategory = :category")
	List<Item> findByFoodItemCategory(@Param("category") String category);

}

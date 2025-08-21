package com.rms.restaurant_management_system_backend.dao;

import java.util.List;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.domain.Items;

public interface ItemsDao {
	int addItem(Items item);

	boolean existsByName(String name);

	Items getItemById(int id);

	int updateItem(Items item);

	int deleteItem(int id);

	List<Items> getAllItems();

	Items changeAvailability(int id, ItemAvailability availability);
}

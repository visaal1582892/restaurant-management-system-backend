package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.domain.Items;

public interface ItemsService {

	int saveItem(Items item);

	Items getItemById(int id);

	Items updateItem(int id, Items item);

	void deleteItem(int id);

	Items changeAvailability(int id, ItemAvailability availability);

	List<Items> getAllItems();

}

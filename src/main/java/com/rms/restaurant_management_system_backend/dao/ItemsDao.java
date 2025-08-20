package com.rms.restaurant_management_system_backend.dao;

import com.rms.restaurant_management_system_backend.domain.Items;

public interface ItemsDao {
	int addItem(Items item);

	boolean existsByName(String name);
}

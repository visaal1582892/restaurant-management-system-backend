package com.rms.restaurant_management_system_backend.service.implementation;

import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.dao.ItemsDao;
import com.rms.restaurant_management_system_backend.domain.Items;
import com.rms.restaurant_management_system_backend.exception.DatabaseOperationException;
import com.rms.restaurant_management_system_backend.exception.DuplicateException;
import com.rms.restaurant_management_system_backend.exception.InvalidDataException;
import com.rms.restaurant_management_system_backend.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {

	private final ItemsDao itemsDao;

	public ItemsServiceImpl(ItemsDao itemsDao) {
		this.itemsDao = itemsDao;
	}

	@Override
	public int saveItem(Items item) {
		if (item == null) {
			throw new InvalidDataException("Items details are wrong");
		}

		if (itemsDao.existsByName(item.getName())) {
			throw new DuplicateException("Item already exists.");
		}
		int itemSaved = itemsDao.addItem(item);

		if (itemSaved <= 0) {
			throw new DatabaseOperationException("Item Not Added Please try again");
		}
		return itemSaved;
	}

}

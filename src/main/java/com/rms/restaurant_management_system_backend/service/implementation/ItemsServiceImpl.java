package com.rms.restaurant_management_system_backend.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.constant.ItemStatus;
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

	@Override
	public Items getItemById(int id) {

		return itemsDao.getItemById(id);
	}

	@Override
	public Items updateItem(int id, Items updatedItem) {
		Items existingItem = itemsDao.getItemById(id);

		existingItem.setName(updatedItem.getName());
		existingItem.setDescription(updatedItem.getDescription());
		existingItem.setPrice(updatedItem.getPrice());
		existingItem.setCategory(updatedItem.getCategory());
		existingItem.setAvailable(updatedItem.getAvailable());
		if (updatedItem.getImageUrl() != null) {
			existingItem.setImageUrl(updatedItem.getImageUrl());
		}

		int updated = itemsDao.updateItem(existingItem);
		if (updated <= 0) {
			throw new DatabaseOperationException("Item Not Updated, Please try again");
		}
		return existingItem;
	}

	@Override
	public void deleteItem(int id) {
		Items existingItem = itemsDao.getItemById(id);

		existingItem.setStatus(ItemStatus.INACTIVE);
		itemsDao.updateItem(existingItem);
	}

	@Override
	public Items changeAvailability(int id, ItemAvailability availability) {
		Items existingItem = itemsDao.getItemById(id);

		existingItem.setAvailable(availability);
		int updated = itemsDao.updateItem(existingItem);
		if (updated <= 0) {
			throw new DatabaseOperationException("Availability Not Updated, Please try again");
		}
		return existingItem;
	}

	@Override
	public List<Items> getAllItems() {
		return itemsDao.getAllItems();
	}

}

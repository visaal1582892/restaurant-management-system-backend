package com.rms.restaurant_management_system_backend.dao.implementation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.constant.ItemStatus;
import com.rms.restaurant_management_system_backend.dao.ItemsDao;
import com.rms.restaurant_management_system_backend.domain.Items;

@Repository
public class ItemsDaoImpl implements ItemsDao {

	private final JdbcTemplate jdbcTemplate;

	public ItemsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final String ITEM_INSERT = "INSERT INTO items (name, image, description, category, availability,status) VALUES (?, ?, ?, ?, ?,?)";
	private final String ITEM_SELECT_BY_NAME = "SELECT COUNT(*) FROM items WHERE  name = ? AND status = 'Active' ";

	@Override
	public int addItem(Items item) {

		return jdbcTemplate.update(ITEM_INSERT, item.getName(), item.getImageUrl(), item.getDescription(),
				item.getCategory().getName(), item.getAvailable().getName(), ItemStatus.ACTIVE.getName());

	}

	@Override
	public boolean existsByName(String name) {

		Integer count = jdbcTemplate.queryForObject(ITEM_SELECT_BY_NAME, Integer.class, name);
		return count != null && count > 0;
	}

}

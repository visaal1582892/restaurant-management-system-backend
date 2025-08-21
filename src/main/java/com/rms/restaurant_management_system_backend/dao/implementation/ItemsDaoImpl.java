package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.constant.ItemCategory;
import com.rms.restaurant_management_system_backend.constant.ItemStatus;
import com.rms.restaurant_management_system_backend.dao.ItemsDao;
import com.rms.restaurant_management_system_backend.domain.Items;
import com.rms.restaurant_management_system_backend.exception.ResourceNotFoundException;

@Repository
public class ItemsDaoImpl implements ItemsDao {

	private final JdbcTemplate jdbcTemplate;

	public ItemsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final String ITEM_INSERT = "INSERT INTO items (name, image, description,price, category, availability,status) VALUES (?, ?,?, ?, ?, ?,?)";
	private final String ITEM_SELECT_BY_NAME = "SELECT COUNT(*) FROM items WHERE  name = ? AND status = 'Active' ";
	private final String ITEM_SELECT_ALL = "SELECT * FROM items WHERE status = 'Active'";
	private final String ITEM_SELECT_BY_ID = "SELECT * FROM items WHERE id = ? AND status = 'Active'";
	private final String ITEM_UPDATE = "UPDATE items SET name = ?, image = ?, description = ?, category = ?, availability = ? WHERE id = ? AND status = 'Active'";
	private final String ITEM_DELETE = "UPDATE items SET status = 'Inactive' WHERE id = ?";

	@Override
	public int addItem(Items item) {

		return jdbcTemplate.update(ITEM_INSERT, item.getName(), item.getImageUrl(), item.getDescription(),
				item.getPrice(), item.getCategory().getName(), ItemAvailability.AVAILABLE.getName(),
				ItemStatus.ACTIVE.getName());

	}

	@Override
	public boolean existsByName(String name) {

		Integer count = jdbcTemplate.queryForObject(ITEM_SELECT_BY_NAME, Integer.class, name);
		return count != null && count > 0;
	}

	@Override
	public Items getItemById(int id) {
		List<Items> items = jdbcTemplate.query(ITEM_SELECT_BY_ID, new BeanPropertyRowMapper<>(Items.class), id);
		if (items.isEmpty()) {
			throw new ResourceNotFoundException("Item with id " + id + " not found");
		}
		return items.get(0);
	}

	@Override
	public int updateItem(Items item) {
		return jdbcTemplate.update(ITEM_UPDATE, item.getName(), item.getImageUrl(), item.getDescription(),
				item.getCategory().getName(), item.getAvailable().getName(), item.getId());
	}

	@Override
	public int deleteItem(int id) {
		return jdbcTemplate.update(ITEM_DELETE, id);
	}

	@Override
	public List<Items> getAllItems() {
		return jdbcTemplate.query(ITEM_SELECT_ALL, new BeanPropertyRowMapper<>(Items.class));
	}

	@Override
	public Items changeAvailability(int id, ItemAvailability availability) {
		String UPDATE_AVAILABILITY = "UPDATE items SET availability = ? WHERE id = ?";

		int rows = jdbcTemplate.update(UPDATE_AVAILABILITY, availability.getName(), id);

		if (rows <= 0) {
			return null;
		}

		String SELECT_ITEM = "SELECT id, name, image, description, category, availability, status "
				+ "FROM items WHERE id = ?";

		return jdbcTemplate.queryForObject(SELECT_ITEM, (rs, rowNum) -> {
			Items item = new Items();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setImageUrl(rs.getString("image"));
			item.setDescription(rs.getString("description"));
			item.setCategory(ItemCategory.fromName(rs.getString("category")));
			item.setAvailable(ItemAvailability.fromName(rs.getString("availability")));
			item.setStatus(ItemStatus.fromName(rs.getString("status")));
			return item;
		}, id);
	}

}

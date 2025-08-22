package com.rms.restaurant_management_system_backend.dao.implementation;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.OrdersDao;
import com.rms.restaurant_management_system_backend.domain.Orders;
import com.rms.restaurant_management_system_backend.rowmappers.OrdersRowMapper;

@Repository
public class OrdersDaoImpl implements OrdersDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addOrder(Orders order) {
		String sql = "INSERT INTO orders (cust_id, wtr_id, ord_date, status) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, order.getCustomerId(), order.getWaiterId(), Date.valueOf(order.getOrderDate()),
				order.getStatus());
	}

	@Override
	public int updateAmount(Orders order, double amount) {
		String sql = "UPDATE orders SET amount = ? WHERE ord_id = ?";
		return jdbcTemplate.update(sql, amount, order.getOrderId());
	}

	@Override
	public int updateStatus(Orders order, String status) {
		String sql = "UPDATE orders SET status = ? WHERE ord_id = ?";
		return jdbcTemplate.update(sql, status, order.getOrderId());
	}

	@Override
	public Orders getOrderById(int id) {
		try {
			String sql = "SELECT * FROM orders WHERE ord_id = ?";
			return jdbcTemplate.queryForObject(sql, new OrdersRowMapper(), id);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<Orders> getAllOrders() {
		String sql = "SELECT * FROM orders";
		return jdbcTemplate.query(sql, new OrdersRowMapper());
	}

	@Override
	public int getOrderId(Orders order) {
		try {
			String sql = "SELECT ord_id FROM orders WHERE cust_id = ? AND wtr_id = ?";
			return jdbcTemplate.queryForObject(sql, Integer.class,
					new Object[] { order.getCustomerId(), order.getWaiterId() });
		} catch (EmptyResultDataAccessException ex) {
			return 0;
		}
	}

	@Override
	public List<Orders> getOrdersByCategory(String category) {
		String sql = "SELECT * FROM orders WHERE status = ?";
		return jdbcTemplate.query(sql, new OrdersRowMapper(), category);
	}

}

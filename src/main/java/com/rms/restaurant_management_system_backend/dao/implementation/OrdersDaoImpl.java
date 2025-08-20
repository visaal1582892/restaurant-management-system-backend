package com.rms.restaurant_management_system_backend.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.constant.OrderStatus;
import com.rms.restaurant_management_system_backend.dao.OrdersDao;
import com.rms.restaurant_management_system_backend.domain.Orders;

@Repository
public class OrdersDaoImpl implements OrdersDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addOrder(Orders order) {
		String sql = "INSERT INTO orders (cust_id, stf_id, ord_date, amount, status) VALUES (?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, order.getCustomerId(), order.getStaffId(), order.getOrderDate(),
				order.getAmount(), order.getStatus());
	}

	@Override
	public int updateOrder(Orders order) {
		String sql = "UPDATE orders SET cust_id = ?, stf_id = ?, ord_date = ?, amount = ?, status = ? WHERE ord_id = ?";
		return jdbcTemplate.update(sql, order.getCustomerId(), order.getStaffId(), order.getOrderDate(),
				order.getAmount(), order.getStatus(), order.getOrderId());
	}

	@Override
	public int deleteOrder(Orders order) {
		String sql = "DELETE FROM orders WHERE ord_id = ?";
		return jdbcTemplate.update(sql, order.getOrderId());
	}

	@Override
	public Orders getOrderById(int id) {
		String sql = "SELECT * FROM orders WHERE ord_id = ?";
		return jdbcTemplate.queryForObject(sql, new OrdersRowMapper());
	}

	@Override
	public List<Orders> getAllOrders() {
		String sql = "SELECT * FROM orders";
		return jdbcTemplate.query(sql, new OrdersRowMapper());
	}

	private static class OrdersRowMapper implements RowMapper<Orders> {

		@Override
		public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
			Orders order = new Orders();
			order.setOrderId(rs.getInt("ord_id"));
			order.setCustomerId(rs.getInt("cust_id"));
			order.setStaffId(rs.getInt("stf_id"));
			order.setOrderDate(rs.getDate("ord_date").toLocalDate());
			order.setAmount(rs.getDouble("amount"));
			order.setStatus(OrderStatus.getEnumConstant(rs.getString("status")));
			return order;
		}
	}

}

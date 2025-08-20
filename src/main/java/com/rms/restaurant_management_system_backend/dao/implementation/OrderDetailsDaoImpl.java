package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.OrderDetailsDao;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final RowMapper<OrderDetails> orderDetailsRowMapper = (rs, rowNum) -> {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderDetailsId(rs.getInt("ord_details_id"));
		orderDetails.setOrderId(rs.getInt("ord_id"));
		orderDetails.setItemId(rs.getInt("item_id"));
		orderDetails.setQuantity(rs.getInt("quantity"));
		orderDetails.setPrice(rs.getDouble("price"));
		return orderDetails;
	};
	
	
	
	@Override
	public List<OrderDetails> getAllOrderDetails() {
		String sql = "select * from order_details";
		 return jdbcTemplate.query(sql, orderDetailsRowMapper);
	}

}

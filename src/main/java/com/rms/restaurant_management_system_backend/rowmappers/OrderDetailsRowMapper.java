package com.rms.restaurant_management_system_backend.rowmappers;

import org.springframework.jdbc.core.RowMapper;

import com.rms.restaurant_management_system_backend.domain.OrderDetails;

public class OrderDetailsRowMapper {
	
	private final RowMapper<OrderDetails> orderDetailsRowMapper = (rs, rowNum) -> {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderDetailsId(rs.getInt("ord_details_id"));
		orderDetails.setOrderId(rs.getInt("ord_id"));
		orderDetails.setItemId(rs.getInt("item_id"));
		orderDetails.setQuantity(rs.getInt("quantity"));
		orderDetails.setPrice(rs.getDouble("price"));
		return orderDetails;
	};
}

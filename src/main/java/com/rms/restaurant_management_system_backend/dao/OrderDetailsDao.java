package com.rms.restaurant_management_system_backend.dao;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.OrderDetails;

public interface OrderDetailsDao {
	
	List<OrderDetails> getAllOrderDetails();
}

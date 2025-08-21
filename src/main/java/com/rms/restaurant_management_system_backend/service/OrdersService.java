package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.Orders;

public interface OrdersService {

	void addOrder(Orders order);

	void updateStatus(Orders order);

	void updateAmount(Orders order, int amount);

	void deleteOrder(Orders order);

	Orders getOrderById(int id);

	List<Orders> getAllOrders();
	
}

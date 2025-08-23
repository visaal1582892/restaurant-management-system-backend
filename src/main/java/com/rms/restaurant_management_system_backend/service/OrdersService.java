package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.constant.OrderStatus;
import com.rms.restaurant_management_system_backend.domain.Orders;

public interface OrdersService {

	int addOrder(String name, String phone, int waiterId);

	void updateAmount(int OrderId);

	Orders getOrderById(int id);

	List<Orders> getAllOrders();

	List<Orders> getOrdersByCategory(String category);

	void updateStatus(int id, OrderStatus status);

}

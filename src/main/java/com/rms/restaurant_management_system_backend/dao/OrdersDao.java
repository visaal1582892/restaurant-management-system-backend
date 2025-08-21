package com.rms.restaurant_management_system_backend.dao;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.Orders;

public interface OrdersDao {

	int addOrder(Orders order);

	int updateStatus(Orders order);

	int updateAmount(Orders order, int amount);

	int deleteOrder(Orders order);

	Orders getOrderById(int id);

	List<Orders> getAllOrders();

}

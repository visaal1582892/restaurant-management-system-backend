package com.rms.restaurant_management_system_backend.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rms.restaurant_management_system_backend.constant.OrderStatus;
import com.rms.restaurant_management_system_backend.dao.OrdersDao;
import com.rms.restaurant_management_system_backend.domain.Orders;
import com.rms.restaurant_management_system_backend.exception.DatabaseOperationException;
import com.rms.restaurant_management_system_backend.exception.InvalidDataException;
import com.rms.restaurant_management_system_backend.exception.ResourceNotFoundException;
import com.rms.restaurant_management_system_backend.service.OrdersService;

public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;

	@Override
	public void addOrder(Orders order) {
		// order - customer id, staff id, amount
		// check customer id, staff id
		if (order.getAmount() <= 0) {
			throw new InvalidDataException("Amount must be greater than zero");
		}
		order.setOrderDate(LocalDate.now());
		order.setStatus(OrderStatus.PENDING);
		int rows = ordersDao.addOrder(order);
		if (rows != 1) {
			throw new DatabaseOperationException("Failed to create new order");
		}
	}

	@Override
	public void updateStatus(Orders order) {
		if (order == null || ordersDao.getOrderById(order.getOrderId()) == null) {
			throw new InvalidDataException("Order with ID " + order.getOrderId() + " does not exist");
		}
		if (!order.getStatus().equals(OrderStatus.PENDING)) {
			throw new InvalidDataException(
					"Cannot update status. Order with ID " + order.getOrderId() + " is already " + order.getStatus());
		}
		int rows = ordersDao.updateStatus(order);
		if (rows != 1) {
			throw new DatabaseOperationException("Failed to update status with ID " + order.getOrderId());
		}
	}

	@Override
	public void updateAmount(Orders order, int amount) {
		if (order == null || ordersDao.getOrderById(order.getOrderId()) == null) {
			throw new InvalidDataException("Order with ID " + order.getOrderId() + " does not exist");
		}
		if (!order.getStatus().equals(OrderStatus.PENDING)) {
			throw new InvalidDataException(
					"Cannot update amount. Order with ID " + order.getOrderId() + " is already " + order.getStatus());
		}
		if (amount <= 0) {
			throw new InvalidDataException("Amount must be greater than zero");
		}
		int rows = ordersDao.updateAmount(order, amount);
		if (rows != 1) {
			throw new DatabaseOperationException("Failed to update amount with ID " + order.getOrderId());
		}
	}

	@Override
	public void deleteOrder(Orders order) {
		if (order == null || ordersDao.getOrderById(order.getOrderId()) == null) {
			throw new InvalidDataException("Order with ID " + order.getOrderId() + " does not exist");
		}
		if (!order.getStatus().equals(OrderStatus.PENDING)) {
			throw new InvalidDataException(
					"Cannot cancel order. Order with ID " + order.getOrderId() + " is already " + order.getStatus());
		}
		int rows = ordersDao.deleteOrder(order);
		if (rows != 1) {
			throw new DatabaseOperationException("Failed to cancel order with ID " + order.getOrderId());
		}
	}

	@Override
	public Orders getOrderById(int id) {
		Orders order = ordersDao.getOrderById(id);
		if (order == null) {
			throw new ResourceNotFoundException("Order with ID " + id + " not found");
		}
		return order;
	}

	@Override
	public List<Orders> getAllOrders() {
		List<Orders> orders = ordersDao.getAllOrders();
		if (orders.isEmpty()) {
			throw new ResourceNotFoundException("No orders found");
		}
		return orders;
	}

}

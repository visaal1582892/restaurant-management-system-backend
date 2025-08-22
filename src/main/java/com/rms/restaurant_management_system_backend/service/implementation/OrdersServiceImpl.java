package com.rms.restaurant_management_system_backend.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.constant.OrderStatus;
import com.rms.restaurant_management_system_backend.dao.OrderDetailsDao;
import com.rms.restaurant_management_system_backend.dao.OrdersDao;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.domain.Orders;
import com.rms.restaurant_management_system_backend.exception.DatabaseOperationException;
import com.rms.restaurant_management_system_backend.exception.InvalidDataException;
import com.rms.restaurant_management_system_backend.exception.ResourceNotFoundException;
import com.rms.restaurant_management_system_backend.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	private final OrdersDao ordersDao;
	private final OrderDetailsDao orderDetailsDao;

	public OrdersServiceImpl(OrdersDao ordersDao, OrderDetailsDao orderDetailsDao) {
		this.ordersDao = ordersDao;
		this.orderDetailsDao=orderDetailsDao;
	}

	@Override
	public void addOrder(Orders order) {
		// order - customer id, waiter id
		// check customer id, waiter id

		order.setOrderDate(LocalDate.now());
		order.setStatus(OrderStatus.PENDING);
		int rows = ordersDao.addOrder(order);
		if (rows != 1) {
			throw new DatabaseOperationException("Failed to create new order");
		}
	}

	@Override
	public void updateStatus(int id, OrderStatus status) {
		Orders order = ordersDao.getOrderById(id);
		if(order == null) {
			throw new InvalidDataException(
					"Order with ID " + order.getOrderId() + " does not exist");
		}
		if (!order.getStatus().equals(OrderStatus.PENDING)) {
			throw new InvalidDataException(
					"Cannot update status. Order with ID " + order.getOrderId() + " is already " + order.getStatus());
		}
		int rows = ordersDao.updateStatus(order, status.getStatus());
		if (rows != 1) {
			throw new DatabaseOperationException("Failed to update status with ID " + order.getOrderId());
		}
	}

	@Override
	public void updateAmount(Orders order) {
		if (order == null) {
			throw new InvalidDataException("Invalid inputs");
		}
		int id = ordersDao.getOrderId(order);
		if (id == 0) {
			throw new InvalidDataException("Order does not exist");
		}
		order.setOrderId(id);
		if (!order.getStatus().equals(OrderStatus.PENDING)) {
			throw new InvalidDataException(
					"Cannot update amount. Order with ID " + order.getOrderId() + " is already " + order.getStatus());
		}
		double amount = calculateTotalAmount(id);
		if (amount <= 0) {
			throw new InvalidDataException("Amount must be greater than zero");
		}
		int rows = ordersDao.updateAmount(order, amount);
		if (rows != 1) {
			throw new DatabaseOperationException("Failed to update amount with ID " + order.getOrderId());
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

	@Override
	public List<Orders> getOrdersByCategory(String category) {
		List<Orders> orders = ordersDao.getOrdersByCategory(category);
		if (orders.isEmpty()) {
			throw new ResourceNotFoundException("No orders found for category: " + category);
		}
		return orders;
	}

	
	public double calculateTotalAmount(int id) {
		double amount = 0;
		List<OrderDetails> details = orderDetailsDao.selectAllOrderDetails().stream()
				.filter(d -> d.getOrderId() == id).collect(Collectors.toList());
		for (OrderDetails detail : details) {
			amount += detail.getQuantity()*detail.getPrice();
		}
		return amount;
	}

}

package com.rms.restaurant_management_system_backend.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.dao.implementation.OrderDetailsDaoImpl;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsDaoImpl orderDetailsDaoImpl;

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsDaoImpl.getAllOrderDetails();
	}

}

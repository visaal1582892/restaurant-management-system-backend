package com.rms.restaurant_management_system_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.service.implementation.OrderDetailsServiceImpl;

@RestController
public class OrderDetailsController {

	@Autowired
	private OrderDetailsServiceImpl orderDetailsServiceImpl;

	@GetMapping
	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsServiceImpl.getAllOrderDetails();
	}
}

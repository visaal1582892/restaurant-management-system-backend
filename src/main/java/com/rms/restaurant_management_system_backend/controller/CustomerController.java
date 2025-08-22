package com.rms.restaurant_management_system_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.service.implementation.CustomerServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/staff/customers")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;

	@PostMapping()
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return ResponseEntity.ok(Map.of("status", "success", "message", "customer added successfully"));
	}
}

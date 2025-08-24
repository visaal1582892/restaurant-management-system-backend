package com.rms.restaurant_management_system_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> 246583a6e4415d9a9f084eb8ed51290f6e3933ef
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.dao.implementation.CustomerDaoImpl;
import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.service.implementation.CustomerServiceImpl;

import jakarta.validation.Valid;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/customers")
=======
@RequestMapping("api/staff/customers")
>>>>>>> 246583a6e4415d9a9f084eb8ed51290f6e3933ef
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;

<<<<<<< HEAD
	@PostMapping("/add")
=======
	@Autowired
	private CustomerDaoImpl customerDao;

	@PostMapping()
>>>>>>> 246583a6e4415d9a9f084eb8ed51290f6e3933ef
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
	
		return ResponseEntity.ok(Map.of("status", "success", "message", "customer added successfully"));
	}

<<<<<<< HEAD
	@GetMapping("/view")
	public ResponseEntity<?> getAllCustomers() {
		customerService.getAllCustomers();
		return ResponseEntity.ok(Map.of("status", "success", "message", "customer fetched successfully"));
=======
	@GetMapping("/{phone}")
	public Integer getId(@PathVariable String phone) {
		return customerDao.getCustomerIdByNumber(phone);
>>>>>>> 246583a6e4415d9a9f084eb8ed51290f6e3933ef
	}
}

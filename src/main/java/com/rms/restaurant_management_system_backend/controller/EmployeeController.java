package com.rms.restaurant_management_system_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.service.implementation.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeService;

	@PostMapping()
	public ResponseEntity<?> addEmployee(@RequestBody Employees employee) {
		employeeService.addEmployee(employee);
		return ResponseEntity.ok(Map.of("status", "success", "message", "member added successfully"));
		
	}

}

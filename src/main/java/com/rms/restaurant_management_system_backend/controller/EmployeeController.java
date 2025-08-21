package com.rms.restaurant_management_system_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.dao.EmployeeDao;
import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.service.implementation.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeService;

	@Autowired
	EmployeeDao empDao;

	@PostMapping("/add")
	public ResponseEntity<?> addEmployee(@RequestBody Employees employee) {
		employeeService.addEmployee(employee);
		return ResponseEntity.ok(Map.of("status", "success", "message", "member added successfully"));

	}

//	@GetMapping
//	public ResponseEntity<?> viewAllEmployees(@RequestBody Employees employee) {
//		employeeService.getAllEmployees(employee);
//		return ResponseEntity.ok(Map.of("status", "success", "message", "member fetched successfully"));
//
//	}

	@GetMapping
	public List<Employees> viewAllEmployees() {
		return employeeService.getAllEmployees();

	}

	@GetMapping("/active")
	public List<Employees> viewActiveEmployees() {
		return employeeService.getActiveEmployees();

	}

	@GetMapping("/{id}")
	public Employees getEmployeeById(@PathVariable int id) {
		return empDao.getEmpById(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employees employee, @PathVariable int id) {
		employeeService.updateEmployee(employee, id);
		return ResponseEntity.ok(Map.of("status", "success", "message", "member updated  successfully"));

	}

	@PutMapping("/update-status/{id}")
	public ResponseEntity<?> updateEmployeeStatus(@RequestBody Employees employee, @PathVariable int id) {
		employeeService.updateEmpStatus(employee, id);
		return ResponseEntity.ok(Map.of("status", "success", "message", "member status updated successfully"));

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok(Map.of("status", "success", "message", "member deleted successfully"));

	}

}

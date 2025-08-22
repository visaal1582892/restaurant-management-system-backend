package com.rms.restaurant_management_system_backend.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.constant.Designation;
import com.rms.restaurant_management_system_backend.dao.EmployeeDao;
import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.exception.DuplicateException;
import com.rms.restaurant_management_system_backend.exception.InvalidDataException;
import com.rms.restaurant_management_system_backend.exception.ResourceNotFoundException;
import com.rms.restaurant_management_system_backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	WaitersServiceImpl waiterService;

	public int addEmployee(Employees employee) {

		if (employee == null) {
			throw new InvalidDataException("Please enter all employees details");
		}

		if (employeeDao.selectByEmail(employee.getEmail())) {
			throw new DuplicateException("Employee Already Exists with this email.");
		}

		if (employeeDao.selectByMobile(employee.getPhone())) {
			throw new DuplicateException("Employee Already exists with this mobile Number");
		}
		int rows = employeeDao.addEmployee(employee);
		if (rows > 0) {
			int empId = employeeDao.getEmployeeIdByEmail(employee.getEmail());
			if (employee.getDesignation() == Designation.WAITER) {
				waiterService.insertWaiter(empId);
			}

		}
		return rows;

	}

	public List<Employees> getAllEmployees() {
		List<Employees> employees = employeeDao.getAllEmployees();
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException("No employees found");
		}
		return employees;
	}

	public List<Employees> getActiveEmployees() {
		List<Employees> employees = employeeDao.getActiveEmployees();
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException("No employees found");
		}
		return employees;

	}

	public int updateEmployee(Employees employee, int id) {

		int rows = employeeDao.updateEmployee(employee, id);
		if (rows == 0) {
			throw new ResourceNotFoundException("No Employee found with this id");
		}
		return rows;
	}

	public int updateEmpStatus(Employees employee, int id) {

		int rows = employeeDao.updateStatus(employee, id);
		if (rows == 0) {
			throw new ResourceNotFoundException("No Employee found with this id");
		}
		return rows;

	}

	public int deleteEmployee(int id) {

		int rows = employeeDao.deleteEmployee(id);
		if (rows == 0) {
			throw new ResourceNotFoundException("No Employee found with this id");
		}
		return rows;

	}

}

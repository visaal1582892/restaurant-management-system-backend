package com.rms.restaurant_management_system_backend.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.dao.implementation.EmployeeDaoImpl;
import com.rms.restaurant_management_system_backend.domain.Employees;

@Service
public class EmployeeServiceImpl {

	@Autowired
	EmployeeDaoImpl employeeDao;

	public int addEmployee(Employees employee) {
		return employeeDao.addEmployee(employee);

	}

	public List<Employees> getAllEmployees() {
		List<Employees> employee = employeeDao.getAllEmployees();
		return employee;

	}

	public List<Employees> getActiveEmployees() {
		List<Employees> employee = employeeDao.getActiveEmployees();
		return employee;
	}

	public int updateEmployee(Employees employee, int id) {
		return employeeDao.updateEmployee(employee, id);
	}

	public int updateEmpStatus(Employees employee, int id) {
		return employeeDao.updateStatus(employee, id);
	}

	public int deleteEmployee(int id) {
		return employeeDao.deleteEmployee(id);
	}

}

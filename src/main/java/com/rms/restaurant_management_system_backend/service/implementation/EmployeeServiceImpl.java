package com.rms.restaurant_management_system_backend.service.implementation;

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

}

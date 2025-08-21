package com.rms.restaurant_management_system_backend.dao;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.Employees;

public interface EmployeeDao {
	public int addEmployee(Employees employee);

	public List<Employees> getAllEmployees();

}

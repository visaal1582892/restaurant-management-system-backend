package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.rowmappers.EmployeeRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class EmployeeDaoImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EmployeeRowMapper employeeRowMapper;

	public int addEmployee(Employees employee) {
		return jdbcTemplate.update(SqlQueries.EMPLOYEE_INSERT, employee.getName(), employee.getEmail(),
				employee.getPhone(), employee.getStatus().getName(), employee.getDesignation().getName(),
				employee.getJoin_date(), employee.getLeaving_date());

	}

	public List<Employees> getAllEmployees() {
		return jdbcTemplate.query(SqlQueries.GET_ALL_EMPLOYEES, employeeRowMapper);
	}

}

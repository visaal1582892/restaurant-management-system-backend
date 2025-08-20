package com.rms.restaurant_management_system_backend.dao.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class EmployeeDaoImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int addEmployee(Employees employee) {
		return jdbcTemplate.update(SqlQueries.MEMBER_INSERT, employee.getName(), employee.getEmail(),
				employee.getPhone(), employee.getStatus().getName(), employee.getDesignation().getName(),
				employee.getJoin_date(), employee.getLeaving_date());

	}

}

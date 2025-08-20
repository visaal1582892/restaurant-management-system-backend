package com.rms.restaurant_management_system_backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.constant.Designation;
import com.rms.restaurant_management_system_backend.constant.EmployeeAvailability;
import com.rms.restaurant_management_system_backend.domain.Employees;

@Repository
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<Employees> employeeRowMapper = (rs, rowNum) -> {

		Employees employee = new Employees();
		employee.setEmpId(rs.getInt("emp_id"));
		employee.setName(rs.getString("name"));
		employee.setEmail(rs.getString("email"));
		employee.setPhone(rs.getString("phone"));
		employee.setAvailability(EmployeeAvailability.getEnumConstant(rs.getString("status")));
		employee.setDesignation(Designation.getEnumConstant(rs.getString("designation")));
		employee.setJoin_date(rs.getDate("join_date"));
		employee.setLeaving_date(rs.getDate("leaving_date"));

		return employee;

	};

}

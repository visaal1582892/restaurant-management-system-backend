package com.rms.restaurant_management_system_backend.dao.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.constant.Designation;
import com.rms.restaurant_management_system_backend.constant.EmployeeStatus;
import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class EmployeeDaoImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<Employees> employeeRowMapper = (rs, rowNum) -> {

		Employees employee = new Employees();
		employee.setEmpId(rs.getInt("emp_id"));
		employee.setName(rs.getString("name"));
		employee.setEmail(rs.getString("email"));
		employee.setPhone(rs.getString("phone"));
		employee.setStatus(EmployeeStatus.getEnumConstant(rs.getString("status")));
		employee.setDesignation(Designation.getEnumConstant(rs.getString("designation")));
		employee.setJoin_date(rs.getDate("join_date"));
		employee.setLeaving_date(rs.getDate("leaving_date"));

		return employee;

	};

	public int addEmployee(Employees employee) {
		return jdbcTemplate.update(SqlQueries.MEMBER_INSERT, employee.getName(), employee.getEmail(),
				employee.getPhone(), employee.getStatus().getName(), employee.getDesignation().getName(),
				employee.getJoin_date(), employee.getLeaving_date());

	}

}

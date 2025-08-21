package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.EmployeeDao;
import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.rowmappers.EmployeeRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EmployeeRowMapper employeeRowMapper;

	@Override
	public int addEmployee(Employees employee) {
		return jdbcTemplate.update(SqlQueries.EMPLOYEE_INSERT, employee.getName(), employee.getEmail(),
				employee.getPhone(), employee.getStatus().getName(), employee.getDesignation().getName(),
				employee.getJoin_date(), employee.getLeaving_date());
	}

	@Override
	public List<Employees> getAllEmployees() {
		return jdbcTemplate.query(SqlQueries.GET_ALL_EMPLOYEES, employeeRowMapper);
	}

	@Override
	public Employees getEmpById(int id) {
		return jdbcTemplate.query(SqlQueries.EMPLOYEE_BY_ID, employeeRowMapper, id).stream().findFirst().orElse(null);
	}

	public List<Employees> getActiveEmployees() {
		return jdbcTemplate.query(SqlQueries.GET_ACTIVE_EMPLOYEES, employeeRowMapper);
	}

	public int updateEmployee(Employees employee, int id) {
		return jdbcTemplate.update(SqlQueries.UPDATE_EMPLOYEE, employee.getName(), employee.getEmail(),
				employee.getPhone(), employee.getDesignation().getName(), employee.getJoin_date(),
				employee.getLeaving_date(), id);
	}

	public int updateStatus(Employees employee, int id) {
		return jdbcTemplate.update(SqlQueries.UPDATE_EMP_STATUS, employee.getStatus().getName(), id);

	}

	public int deleteEmployee(int id) {
		return jdbcTemplate.update(SqlQueries.DELETE_EMPLOYEE, id);
	}

	public boolean selectByEmail(String email) {
		Integer count = jdbcTemplate.queryForObject(SqlQueries.EMPLOYEE_SELECT_BY_EMAIL, Integer.class, email);
		return count != null && count > 0;
	}

	public boolean selectByMobile(String mobile) {
		Integer count = jdbcTemplate.queryForObject(SqlQueries.EMPLOYEE_SELECT_BY_MOBILE, Integer.class, mobile);
		return count != null && count > 0;
	}

	public int getEmployeeIdByEmail(String email) {
		return jdbcTemplate.queryForObject(SqlQueries.GET_EMPID_BY_EMAIL, Integer.class, email);
	}
}

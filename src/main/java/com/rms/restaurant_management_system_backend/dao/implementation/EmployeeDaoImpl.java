package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
	NamedParameterJdbcTemplate template;

	@Autowired
	private EmployeeRowMapper employeeRowMapper;

//	@Override
//	public int addEmployee(Employees employee) {
//		return jdbcTemplate.update(SqlQueries.EMPLOYEE_INSERT, employee.getName(), employee.getEmail(),
//				employee.getPhone(), employee.getStatus().getName(), employee.getDesignation().getName(),
//				employee.getJoin_date(), employee.getLeaving_date());
//	}

	@Override
	public int addEmployee(Employees employee) {

//		 Using Map
		Map<String, Object> map = new HashMap<>();
		map.put("name", employee.getName());
		map.put("email", employee.getEmail());
		map.put("phone", employee.getPhone());
		map.put("status", employee.getStatus().getName());
		map.put("designation", employee.getDesignation().getName());
		map.put("join_date", employee.getJoin_date());
		map.put("leaving_date", employee.getLeaving_date());
		return template.update(SqlQueries.EMPLOYEE_INSERT, map);

		// MapSqlParameterSource
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("name", employee.getName()).addValue("email", employee.getEmail())
//				.addValue("phone", employee.getPhone()).addValue("status", employee.getStatus().getName())
//				.addValue("designation", employee.getDesignation().getName())
//				.addValue("join_date", employee.getJoin_date()).addValue("leaving_date", employee.getLeaving_date());
//		return template.update(SqlQueries.EMPLOYEE_INSERT, params);

		// Using BeanPropertySqlParameterSource

//		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(employee);
//		return template.update(SqlQueries.EMPLOYEE_INSERT, params);
	}

	@Override
	public List<Employees> getAllEmployees() {
		return template.query(SqlQueries.GET_ALL_EMPLOYEES, employeeRowMapper);
	}

	@Override
	public Employees getEmpById(int id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return template.query(SqlQueries.EMPLOYEE_BY_ID, params, employeeRowMapper).stream().findFirst().orElse(null);
	}

	@Override
	public List<Employees> getActiveEmployees() {
		return template.query(SqlQueries.GET_ACTIVE_EMPLOYEES, employeeRowMapper);
	}

	@Override
	public int updateEmployee(Employees employee, int id) {
		employeeLog(id);
		return jdbcTemplate.update(SqlQueries.UPDATE_EMPLOYEE, employee.getName(), employee.getEmail(),
				employee.getPhone(), employee.getStatus().getName(), employee.getDesignation().getName(),
				employee.getJoin_date(), employee.getLeaving_date(), id);
	}

	@Override
	public int updateStatus(Employees employee, int id) {
		employeeLog(id);
		return jdbcTemplate.update(SqlQueries.UPDATE_EMP_STATUS, employee.getStatus().getName(), id);

	}

	@Override
	public int deleteEmployee(int id) {
		employeeLog(id);
		return jdbcTemplate.update(SqlQueries.DELETE_EMPLOYEE, id);
	}

	@Override
	public boolean selectByEmail(String email) {
		Integer count = jdbcTemplate.queryForObject(SqlQueries.EMPLOYEE_SELECT_BY_EMAIL, Integer.class, email);
		return count != null && count > 0;
	}

	@Override
	public boolean selectByMobile(String mobile) {
		Integer count = jdbcTemplate.queryForObject(SqlQueries.EMPLOYEE_SELECT_BY_MOBILE, Integer.class, mobile);
		return count != null && count > 0;
	}

	@Override
	public int getEmployeeIdByEmail(String email) {
		return jdbcTemplate.queryForObject(SqlQueries.GET_EMPID_BY_EMAIL, Integer.class, email);
	}

	@Override
	public int employeeLog(int empId) {

		return jdbcTemplate.update(SqlQueries.EMPLOYEE_LOG, empId);
	}

}

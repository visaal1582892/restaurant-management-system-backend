package com.rms.restaurant_management_system_backend.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	public Employees addEmployee(Employees employee) {
	    KeyHolder keyHolder = new GeneratedKeyHolder();
//		jdbcTemplate.update(SqlQueries.EMPLOYEE_INSERT, employee.getName(), employee.getEmail(),
//				employee.getPhone(), employee.getStatus().getName(), employee.getDesignation().getName(),
//				employee.getJoin_date(), employee.getLeaving_date(), keyHolder);
	    jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SqlQueries.EMPLOYEE_INSERT, new String[]{"id"});
                ps.setString(1, employee.getName());
                ps.setString(2, employee.getEmail());
                ps.setString(3, employee.getPhone());
                ps.setString(4, employee.getStatus().getName());
                ps.setString(5, employee.getDesignation().getName());
                ps.setDate(6, employee.getJoin_date());
                ps.setDate(7, employee.getLeaving_date());
                return ps;
            }
        }, keyHolder);
	    employee.setEmpId(keyHolder.getKey().intValue());
	    return employee;
	}

	@Override
	public List<Employees> getAllEmployees() {
		return jdbcTemplate.query(SqlQueries.GET_ALL_EMPLOYEES, employeeRowMapper);
	}

	@Override
	public Employees getEmpById(int id) {
		return jdbcTemplate.query(SqlQueries.EMPLOYEE_BY_ID, employeeRowMapper, id).stream().findFirst().orElse(null);
	}

	@Override
	public List<Employees> getActiveEmployees() {
		return jdbcTemplate.query(SqlQueries.GET_ACTIVE_EMPLOYEES, employeeRowMapper);
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

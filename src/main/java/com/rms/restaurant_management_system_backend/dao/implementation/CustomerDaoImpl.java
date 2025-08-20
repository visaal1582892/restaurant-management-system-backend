package com.rms.restaurant_management_system_backend.dao.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.CustomerDao;
import com.rms.restaurant_management_system_backend.domain.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
		Customer customer = new Customer();
		customer.setCustId(rs.getInt("cust_Id"));
		customer.setName(rs.getString("name"));
		customer.setPhone(rs.getString("phone"));
		return customer;
	};

	@Override
	public int addCustomer(Customer customer) {
		String sql = "insert into customers(name,phone) values(?,?)";
		int rows = jdbcTemplate.update(sql, customer.getName(), customer.getPhone());
		return rows;
	}
		
}

package com.rms.restaurant_management_system_backend.dao.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.CustomerDao;
import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public int addCustomer(Customer customer) {
		return jdbcTemplate.update(SqlQueries.CUSTOMER_INSERT, customer.getName(),
				customer.getPhone());
	}
		
}

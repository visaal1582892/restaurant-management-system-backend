package com.rms.restaurant_management_system_backend.rowmappers;

import org.springframework.jdbc.core.RowMapper;

import com.rms.restaurant_management_system_backend.domain.Customer;

public class CustomerRowMapper {
	private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
		Customer customer = new Customer();
		customer.setCustId(rs.getInt("cust_Id"));
		customer.setName(rs.getString("name"));
		customer.setPhone(rs.getString("phone"));
		return customer;
	};

}

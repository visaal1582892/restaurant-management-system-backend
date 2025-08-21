package com.rms.restaurant_management_system_backend.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rms.restaurant_management_system_backend.domain.Customer;

public class CustomerRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustId(rs.getInt("cust_Id"));
		customer.setName(rs.getString("name"));
		customer.setPhone(rs.getString("phone"));
		
		return customer;
		
	}
	

}

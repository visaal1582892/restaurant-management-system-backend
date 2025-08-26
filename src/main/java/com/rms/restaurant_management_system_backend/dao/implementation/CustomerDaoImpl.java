package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.CustomerDao;
import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;
import com.rms.restaurant_management_system_backend.rowmappers.CustomerRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public CustomerDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Autowired
	private CustomerRowMapper customerRowMapper;

	@Override
	public int addCustomer(Customer customer) {
		return jdbcTemplate.update(SqlQueries.CUSTOMER_INSERT, customer.getName(), customer.getPhone());
	}

	@Override
	public List<Customer> getAllCustomers() {
//		return jdbcTemplate.query(SqlQueries.GET_ALL_CUSTOMERS, customerRowMapper);
		return getCustomers(null, null, null);
	}

	@Override
	public Customer getCustomerById(int id) {
//		List<Customer> customer = jdbcTemplate.query(SqlQueries.GET_CUST_BY_ID,
//				new BeanPropertyRowMapper<>(Customer.class), id);
		List<Customer> customer = getCustomers(id, null, null);
		if (customer.isEmpty()) {
			throw new RestaurantOperationException("customer with id " + id + " not found");
		}
		return customer.get(0);
	}

	public Integer getCustomerIdByNumber(String phone) {
//		return jdbcTemplate.queryForObject(SqlQueries.GET_ID_BY_PHONE, Integer.class, phone);
		List<Customer> customer = getCustomers(null, null, phone);
		return customer.get(0).getCustId();
	}

	private List<Customer> getCustomers(Integer cust_id, String name, String phone) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("hasCustId", cust_id != null);
		params.put("hasName", name != null && !name.isEmpty());
		params.put("hasPhone", phone != null && !phone.isEmpty());
		
		params.put("custId", cust_id);
		params.put("name", name);
		params.put("phone", phone);
		
		return namedParameterJdbcTemplate.query(SqlQueries.CUSTOMERS, params, customerRowMapper);
	}
}

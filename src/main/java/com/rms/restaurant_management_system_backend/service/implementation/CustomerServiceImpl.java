package com.rms.restaurant_management_system_backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.dao.implementation.CustomerDaoImpl;
import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDaoImpl customerDaoImpl;

	@Override
	public int addCustomer(Customer customer) {
		int rows = customerDaoImpl.addCustomer(customer);
		if (rows > 0) {
			int id = customerDaoImpl.getCustomerIdByNumber(customer.getPhone());
			return id;
		}
		return rows;
	}

}

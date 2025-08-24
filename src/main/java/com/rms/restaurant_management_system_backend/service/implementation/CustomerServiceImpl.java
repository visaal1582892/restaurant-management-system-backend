package com.rms.restaurant_management_system_backend.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.dao.implementation.CustomerDaoImpl;
import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.exception.InvalidDataException;
import com.rms.restaurant_management_system_backend.exception.ResourceNotFoundException;
import com.rms.restaurant_management_system_backend.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDaoImpl customerDaoImpl;

	@Override
	public int addCustomer(Customer customer) {
<<<<<<< HEAD

		if (customer == null) {
			throw new InvalidDataException("Please enter all customer details");
		}
		return customerDaoImpl.addCustomer(customer);
=======
		int rows = customerDaoImpl.addCustomer(customer);
		if (rows > 0) {
			int id = customerDaoImpl.getCustomerIdByNumber(customer.getPhone());
			return id;
		}
		return rows;
>>>>>>> 246583a6e4415d9a9f084eb8ed51290f6e3933ef
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerDaoImpl.getAllCustomers();
		if (customers == null || customers.isEmpty()) {
			throw new ResourceNotFoundException("No customers found");
		}
		return customers;
	}
	@Override
	public Customer getCustomerById(int id) {
		return customerDaoImpl.getCustomerById(id);
	}

}

package com.rms.restaurant_management_system_backend.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.dao.OrderDetailsDao;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.exception.DatabaseOperationException;
import com.rms.restaurant_management_system_backend.exception.InvalidDataException;
import com.rms.restaurant_management_system_backend.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	private OrderDetailsDao orderDetailsDao;
	
	public OrderDetailsServiceImpl(OrderDetailsDao orderDetailsDao) {
		this.orderDetailsDao=orderDetailsDao;
	}

	@Override
	public boolean insertOrderDetails(List<OrderDetails> orderDetailsList) {
		
		if(orderDetailsList==null) {
			throw new InvalidDataException("Order details list cannot be null");
		}
		
		if(orderDetailsList.size()==0) {
			throw new InvalidDataException("No order details were found");
		}
		
		int [] countArray=orderDetailsDao.insertOrderDetails(orderDetailsList);
		if(Arrays.stream(countArray).sum()!=orderDetailsList.size()) {
			throw new DatabaseOperationException("All the Order Details are not inserted correctly");
		}
		
		return true;
	}

	@Override
	public List<OrderDetails> selectAllOrderDetails() {
		return orderDetailsDao.selectAllOrderDetails();
	}
	
	
	

//	@Override
//	public List<OrderDetails> getAllOrderDetails() {
//		return orderDetailsDaoImpl.getAllOrderDetails();
//	}

}

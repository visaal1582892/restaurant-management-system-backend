package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.OrderDetailsDao;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	@Override
	public List<OrderDetails> getAllOrderDetails() {
	    return jdbcTemplate.query(SqlQueries.GET_ALL_ORDERDETAILS,
	        (rs, rowNum) -> {
	            OrderDetails od = new OrderDetails();
	            od.setOrderDetailsId(rs.getInt("ord_details_id"));
	            od.setOrderId(rs.getInt("ord_id"));
	            od.setItemId(rs.getInt("item_id"));
	            od.setQuantity(rs.getInt("quantity"));
	            od.setPrice(rs.getDouble("price"));
	            return od;
	        });
	}

		
	}


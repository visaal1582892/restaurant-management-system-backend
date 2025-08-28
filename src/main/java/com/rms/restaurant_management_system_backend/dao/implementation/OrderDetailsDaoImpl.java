package com.rms.restaurant_management_system_backend.dao.implementation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.custom_classes.OrderDetailsSearch;
import com.rms.restaurant_management_system_backend.dao.OrderDetailsDao;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.rowmappers.OrderDetailsRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public OrderDetailsDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int[] insertOrderDetails(List<OrderDetails> orderDetailsList) {
		String insertQuery = SqlQueries.ORDER_DETAILS_INSERT;
		return jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				OrderDetails orderDetails = orderDetailsList.get(i);
				ps.setInt(1, orderDetails.getOrderId());
				ps.setInt(2, orderDetails.getItemId());
				ps.setInt(3, orderDetails.getQuantity());
				ps.setDouble(4, orderDetails.getPrice());
			}

			@Override
			public int getBatchSize() {
				return orderDetailsList.size();
			}
		});
	}

	@Override
	public List<OrderDetails> selectAllOrderDetails() {
//		String selectQuery = SqlQueries.ORDER_DETAILS_SELECT;
//		return jdbcTemplate.query(selectQuery, new OrderDetailsRowMapper());
		OrderDetailsSearch orderDetails = new OrderDetailsSearch();
		return getOrderDetails(orderDetails);
	}

//	@Override
//	public List<OrderDetails> getAllOrderDetails() {
//		return jdbcTemplate.query(SqlQueries.GET_ALL_ORDERDETAILS,
//	        (rs, rowNum) -> {
//	            OrderDetails od = new OrderDetails();
//	            od.setOrderDetailsId(rs.getInt("ord_details_id"));
//	            od.setOrderId(rs.getInt("ord_id"));
//	            od.setItemId(rs.getInt("item_id"));
//	            od.setQuantity(rs.getInt("quantity"));
//	            od.setPrice(rs.getDouble("price"));
//	            return od;
//	        });
//	}

	private List<OrderDetails> getOrderDetails(OrderDetailsSearch orderDetails) {

		Map<String, Object> params = new HashMap<>();
		params.put("hasOrderDetailsId", orderDetails.getOrderDetailsId() != null);
		params.put("hasOrderId", orderDetails.getOrderId() != null);
		params.put("hasItemId", orderDetails.getItemId() != null);
		params.put("hasQuantity", orderDetails.getQuantity() != null);
		params.put("hasPrice", orderDetails.getPrice() != null);

		params.put("orderDetailsId", orderDetails.getOrderDetailsId());
		params.put("orderId", orderDetails.getOrderId());
		params.put("itemId", orderDetails.getItemId());
		params.put("quantity", orderDetails.getQuantity());
		params.put("price", orderDetails.getPrice());

		return namedParameterJdbcTemplate.query(SqlQueries.ORDER_DETAILS, params, new OrderDetailsRowMapper());
	}

}

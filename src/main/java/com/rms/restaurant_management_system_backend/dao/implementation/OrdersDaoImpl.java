package com.rms.restaurant_management_system_backend.dao.implementation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.OrdersDao;
import com.rms.restaurant_management_system_backend.domain.Orders;
import com.rms.restaurant_management_system_backend.rowmappers.OrdersRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class OrdersDaoImpl implements OrdersDao {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public OrdersDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int addOrder(Orders order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(SqlQueries.ORDER_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getCustomerId());
			ps.setInt(2, order.getWaiterId());
			ps.setDate(3, Date.valueOf(order.getOrderDate()));
			ps.setDouble(4, order.getAmount());
			ps.setString(5, order.getStatus().getStatus());
			return ps;
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public int updateAmount(Orders order, double amount) {
		return jdbcTemplate.update(SqlQueries.UPDATE_ORDER_AMOUNT, amount, order.getOrderId());
	}

	@Override
	public int updateStatus(Orders order, String status) {
		return jdbcTemplate.update(SqlQueries.UPDATE_ORDER_STATUS, status, order.getOrderId());
	}

	@Override
	public Orders getOrderById(int id) {
//		try {
//			return jdbcTemplate.queryForObject(SqlQueries.ORDER_BY_ID, new OrdersRowMapper(), id);
//		} catch (EmptyResultDataAccessException ex) {
//			return null;
//		}
		List<Orders> orders = getOrders(id, null, null, null, null, null, null);
		if(orders.size() == 1) {
			return orders.get(0);
		}
		else {
			return null;
		}
	}

	@Override
	public List<Orders> getAllOrders() {
		// return jdbcTemplate.query(SqlQueries.ALL_ORDERS, new OrdersRowMapper());
		return getOrders(null, null, null, null, null, null, null);
	}

	@Override
	public int getOrderId(Orders order) {
//		try {
//			return jdbcTemplate.queryForObject(SqlQueries.GET_ORDERID, Integer.class,
//					new Object[] { order.getCustomerId(), order.getWaiterId() });
//		} catch (EmptyResultDataAccessException ex) {
//			return 0;
//		}
		List<Orders> orders = getOrders(null, order.getCustomerId(), order.getWaiterId(), null, null, null, Arrays.asList("Pending"));
		if(orders.size() == 1) {
			return orders.get(0).getOrderId();
		}
		else {
			return 0;
		}
	}

	@Override
	public List<Orders> getOrdersByCategory(String category) {
//		return jdbcTemplate.query(SqlQueries.ORDERS_BY_CATEGORY, new OrdersRowMapper(), category);
		return getOrders(null, null, null, null, null, null, Arrays.asList(category));
	}

	
	private List<Orders> getOrders(Integer orderId, Integer customerId, Integer waiterId, LocalDate startDate,
			LocalDate endDate, Double amount, List<String> statuses) {

		Map<String, Object> params = new HashMap<>();

		params.put("hasOrderId", orderId != null);
		params.put("hasCustomerId", customerId != null);
		params.put("hasWaiterId", waiterId != null);
		params.put("hasStartDate", startDate != null);
		params.put("hasEndDate", endDate != null);
		params.put("hasAmount", amount != null);
		params.put("hasStatuses", (statuses != null && !statuses.isEmpty()));

		params.put("orderId", orderId);
		params.put("customerId", customerId);
		params.put("waiterId", waiterId);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("amount", amount);
		params.put("statuses", statuses);

		return namedParameterJdbcTemplate.query(SqlQueries.ORDERS, params, new OrdersRowMapper());
	}

	
	@Override
	public void insertLog(Orders order) {
		jdbcTemplate.update(SqlQueries.ORDER_LOG, order.getOrderId(), order.getCustomerId(), order.getWaiterId(),
				Date.valueOf(order.getOrderDate()), order.getAmount(), order.getStatus().getStatus());
	}

}

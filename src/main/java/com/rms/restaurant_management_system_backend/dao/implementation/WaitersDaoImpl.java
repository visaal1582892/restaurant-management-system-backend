package com.rms.restaurant_management_system_backend.dao.implementation;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rms.restaurant_management_system_backend.dao.WaitersDao;
import com.rms.restaurant_management_system_backend.domain.Waiters;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

public class WaitersDaoImpl implements WaitersDao {

	private final JdbcTemplate jdbcTemplate;
	
	public WaitersDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@Override
	public int insertWaiter(int employeeId) {
		String insertQuery=SqlQueries.WAITER_INSERT;
		return jdbcTemplate.update(insertQuery,employeeId);
	}

	@Override
	public int updateWaiterAvailability(int waiterId,String availability) {
		String updateQuery=SqlQueries.WAITER_UPDATE_AVAILABILITY;
		int count=jdbcTemplate.update(updateQuery,availability,waiterId);
		return count;
	}

	@Override
	public int insertWaiterLog(Waiters oldWaiter) {
		String insertQuery=SqlQueries.WAITER_LOG_INSERT;
		int count=jdbcTemplate.update(insertQuery,oldWaiter.getWaiterId(),oldWaiter.getEmployeeId(),oldWaiter.getAvailability());
		return count;
	}

	@Override
	public int selectAssignedOrdersCount(int waiterId) {
		String selectQuery=SqlQueries.COUNT_WAITER_ASSIGNED_ORDERS;
		int count=jdbcTemplate.queryForObject(selectQuery, Integer.class, waiterId);
		return count;
	}

}

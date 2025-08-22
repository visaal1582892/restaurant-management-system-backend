package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.Waiters;

public interface WaitersService {
	boolean insertWaiter(int employeeId);

	boolean updateWaiterAvailability(int waiterId);
	
	List<Waiters> selectAvailableWaiters();
	
	boolean deleteWaiterByEmpId(int employeeId);
}

package com.rms.restaurant_management_system_backend.service;

import com.rms.restaurant_management_system_backend.domain.Waiters;

public interface WaitersService {
	boolean insertWaiter(int employeeId);
	
	boolean updateWaiterAvailability(Waiters oldWaiter);
}

package com.rms.restaurant_management_system_backend.service;

public interface WaitersService {
	boolean insertWaiter(int employeeId);

	boolean updateWaiterAvailability(int waiterId);
}

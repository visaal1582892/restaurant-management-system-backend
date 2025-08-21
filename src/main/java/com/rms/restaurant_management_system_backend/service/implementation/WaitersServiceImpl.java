package com.rms.restaurant_management_system_backend.service.implementation;

import com.rms.restaurant_management_system_backend.dao.WaitersDao;
import com.rms.restaurant_management_system_backend.domain.Waiters;
import com.rms.restaurant_management_system_backend.service.WaitersService;

public class WaitersServiceImpl implements WaitersService {

	private final WaitersDao waitersDao;
	
	public WaitersServiceImpl(WaitersDao waitersDao) {
		this.waitersDao=waitersDao;
	}
	
	@Override
	public boolean insertWaiter(int employeeId) {
		int count=waitersDao.insertWaiter(employeeId);
		return true;
	}

	@Override
	public boolean updateWaiterAvailability(Waiters oldWaiter) {
		// TODO Auto-generated method stub
		return false;
	}

}

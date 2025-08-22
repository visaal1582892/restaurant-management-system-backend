package com.rms.restaurant_management_system_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rms.restaurant_management_system_backend.domain.Waiters;
import com.rms.restaurant_management_system_backend.service.WaitersService;

@RequestMapping("/api/staff/waiters")
public class WaitersController {
	
	private WaitersService waitersService;
	
	public WaitersController(WaitersService waitersService) {
		this.waitersService=waitersService;
	}
	
	@GetMapping("/available")
	public List<Waiters> getAllAvailableWaiters(){
		List<Waiters> availableWaiters=waitersService.selectAvailableWaiters();
		return availableWaiters;
	}
}

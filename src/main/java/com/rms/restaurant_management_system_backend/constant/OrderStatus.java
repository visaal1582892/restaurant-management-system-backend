package com.rms.restaurant_management_system_backend.constant;

import java.util.stream.Stream;

public enum OrderStatus {
	PENDING("Pending"), CANCELLED("Cancelled"), COMPLETED("Completed");

	private String status;

	OrderStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static OrderStatus getEnumConstant(String value) {
		return Stream.of(OrderStatus.values()).filter(e -> e.getStatus().equals(value)).findFirst().orElse(null);
	}
}

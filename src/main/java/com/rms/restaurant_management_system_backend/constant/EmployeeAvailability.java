package com.rms.restaurant_management_system_backend.constant;

import java.util.stream.Stream;

public enum EmployeeAvailability {

	ACTIVE("Active"), INACTIVE("Inactive");

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private EmployeeAvailability(String name) {
		this.name = name;
	}

	public static EmployeeAvailability getEnumConstant(String value) {
		return Stream.of(EmployeeAvailability.values()).filter(e -> e.getName().equals(value)).findFirst().orElse(null);
	}
}

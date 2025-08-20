package com.rms.restaurant_management_system_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rms.restaurant_management_system_backend.exception.InvalidDataException;

public enum ItemCategory {

	;
	private String name;

	ItemCategory(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static ItemCategory fromName(String name) {

		for (ItemCategory category : ItemCategory.values()) {
			if (category.name.equalsIgnoreCase(name)) {
				return category;
			}
		}
		throw new InvalidDataException("Select Item Category from Options");
	}
}

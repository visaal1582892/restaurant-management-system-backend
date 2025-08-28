package com.rms.restaurant_management_system_backend.custom_classes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsSearch {
	private Integer orderDetailsId;
	private Integer orderId;
	private Integer itemId;
	private Integer quantity;
	private Double price;
}

package com.rms.restaurant_management_system_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	
	private int orderDetailsId;
	private int orderId;
	private int itemId;
	private int quantity;
	private double price;
}

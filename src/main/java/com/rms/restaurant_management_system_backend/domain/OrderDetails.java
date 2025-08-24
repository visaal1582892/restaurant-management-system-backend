package com.rms.restaurant_management_system_backend.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	
	private int orderDetailsId;
	@NotBlank(message = "Order id is required")
	private int orderId;
	@NotBlank(message = "Item id is required")
	private int itemId;
	private int quantity;
	private double price;
}

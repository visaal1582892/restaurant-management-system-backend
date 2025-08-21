package com.rms.restaurant_management_system_backend.domain;

import java.time.LocalDate;

import com.rms.restaurant_management_system_backend.constant.OrderStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	
	private int orderId;
	
	@NotBlank(message = "Customer id is required")
	private int customerId;
	
	@NotBlank(message = "Staff id is required")
	private int staffId;
	
	@NotBlank(message = "Order Date is required")
	private LocalDate orderDate;
	
	@NotBlank(message = "Amount is required")
	@Positive(message = "Amount must be a positive number")
	private double amount;
	
	@NotBlank(message = "Status is required")
	private OrderStatus status;
}

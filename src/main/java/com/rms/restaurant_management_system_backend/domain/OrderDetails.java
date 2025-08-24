package com.rms.restaurant_management_system_backend.domain;

<<<<<<< HEAD
import jakarta.validation.constraints.*;
=======
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
>>>>>>> 246583a6e4415d9a9f084eb8ed51290f6e3933ef
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	
	private int orderDetailsId;
<<<<<<< HEAD
	@NotBlank(message = "Order id is required")
	private int orderId;
	@NotBlank(message = "Item id is required")
=======
	
	@NotNull(message="Order id cannot be null")
	private int orderId;
	
	@NotNull(message="Item id cannot be null")
>>>>>>> 246583a6e4415d9a9f084eb8ed51290f6e3933ef
	private int itemId;
	
	@NotNull(message="Item quantity cannot be null")
	@Min(value = 1,message = "Quantity must atleast one")
	private int quantity;
	
	@NotNull(message = "Price cannot be null")
	@Min(value = 1, message = "Price must be greater than or equal to 1")
	private double price;
}

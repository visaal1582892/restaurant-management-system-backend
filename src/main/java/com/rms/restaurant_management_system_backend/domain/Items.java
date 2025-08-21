package com.rms.restaurant_management_system_backend.domain;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.constant.ItemCategory;
import com.rms.restaurant_management_system_backend.constant.ItemStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Items {

	private int id;

	@NotBlank(message = "Name cannot be Empty")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 length")
	@Pattern(regexp = "[a-zA-Z -.()]{0,50}", message = "Name only contain aplhabates -.()")
	private String name;

	private String imageUrl;

	@NotBlank(message = "Description cannot be Empty")
	@Size(min = 3, max = 100, message = "Description must be between 3 and 100 length")
	@Pattern(regexp = "[a-zA-Z0-9 :\\-.'&/,?!+]{0,50}", message = "Title only contain aplhabates,numbers -.&,?!+")
	private String description;

	@NotNull(message = "Price is required")
	private Double price;

	private ItemStatus status;

	private ItemAvailability available;

	@NotNull(message = "Category is required")
	private ItemCategory category;

}

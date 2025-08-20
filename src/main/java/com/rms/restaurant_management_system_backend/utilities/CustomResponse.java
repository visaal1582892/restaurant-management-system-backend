package com.rms.restaurant_management_system_backend.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {

	private boolean success;
	private Object message;
	private Object data;
}

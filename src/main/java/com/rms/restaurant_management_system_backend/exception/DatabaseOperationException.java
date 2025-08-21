package com.rms.restaurant_management_system_backend.exception;

public class DatabaseOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseOperationException(String message) {
		super(message);
	}

}

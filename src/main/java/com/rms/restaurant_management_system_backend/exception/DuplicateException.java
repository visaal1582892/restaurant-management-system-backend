package com.rms.restaurant_management_system_backend.exception;

public class DuplicateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateException(String message) {
		super(message);
	}

	public DuplicateException(String message, Throwable cause) {
		super(message, cause);
	}
}

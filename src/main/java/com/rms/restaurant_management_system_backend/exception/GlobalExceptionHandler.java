package com.rms.restaurant_management_system_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomResponse> handleResourceNotFound(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(false, ex.getMessage(), null));
	}

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<CustomResponse> handleInvalidData(InvalidDataException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(false, ex.getMessage(), null));
	}

	@ExceptionHandler(DatabaseOperationException.class)
	public ResponseEntity<CustomResponse> handleDatabaseError(DatabaseOperationException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new CustomResponse(false, ex.getMessage(), null));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomResponse> handleGeneralError(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new CustomResponse(false, "Unexpected error: " + ex.getMessage(), null));
	}
}

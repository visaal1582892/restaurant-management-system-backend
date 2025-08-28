package com.rms.restaurant_management_system_backend.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

@ControllerAdvice
@Order(1)
public class GlobalExceptionHandler {
	@ExceptionHandler(DatabaseOperationException.class)
	public ResponseEntity<CustomResponse> handleDatabaseOperationException(DatabaseOperationException e) {
		return buildResponse(HttpStatus.CONFLICT, false, e.getMessage());
	}

	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<CustomResponse> handleDuplicateException(DuplicateException e) {
		return buildResponse(HttpStatus.CONFLICT, false, e.getMessage());
	}

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<CustomResponse> handleInvalidDataException(InvalidDataException e) {
		return buildResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
		return buildResponse(HttpStatus.NOT_FOUND, false, e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
				.collect(Collectors.toList());
		return buildResponse(HttpStatus.BAD_REQUEST, false, errors);
	}

	private ResponseEntity<CustomResponse> buildResponse(HttpStatus statusCode, Boolean status, String message) {
		return buildResponse(statusCode, status, List.of(message));
	}

	private ResponseEntity<CustomResponse> buildResponse(HttpStatus statusCode, boolean status, List<String> messages) {
		CustomResponse body = new CustomResponse(status, messages, null);
		return new ResponseEntity<>(body, statusCode);
	}
}

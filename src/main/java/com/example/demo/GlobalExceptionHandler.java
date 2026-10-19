package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {

		String message = e.getBindingResult()
				           .getFieldError()
				           .getDefaultMessage();
				
		ErrorResponse error = new ErrorResponse(message, 400);
		   return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}	 
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeNotFound(
			EmployeeNotFoundException e) {
		ErrorResponse error = new ErrorResponse(e.getMessage(), 404);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}

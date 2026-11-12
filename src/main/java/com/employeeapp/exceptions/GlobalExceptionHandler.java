package com.employeeapp.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<String> handleEmployeeNotFound(DuplicateUserException e) {
		return ResponseEntity.status(409).body(e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidateException(MethodArgumentNotValidException e) {
		
		String error= e.getBindingResult().getFieldErrors().stream().map(err->err.getField()+": "+err.getDefaultMessage()).findFirst().orElse("Invalid details");
		
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
		
		return ResponseEntity.badRequest().body("Invalid value: "+e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleOverAllExceptions(Exception e) {
		return ResponseEntity.status(500).body("Something went wrong!");
	}
	
	
}

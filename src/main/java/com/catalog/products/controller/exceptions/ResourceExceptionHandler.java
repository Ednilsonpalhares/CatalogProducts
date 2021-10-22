package com.catalog.products.controller.exceptions;

import com.catalog.products.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<HttpStatus> objectNotFound(ObjectNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e){
		
		ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Invalid request");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
}

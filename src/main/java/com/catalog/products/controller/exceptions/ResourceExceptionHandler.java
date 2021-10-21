package com.catalog.products.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.catalog.products.service.exceptions.ObjectNotFoundExceptioin;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundExceptioin.class)
	public ResponseEntity<HttpStatus> objectNotFound(ObjectNotFoundExceptioin e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validatioin error");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}

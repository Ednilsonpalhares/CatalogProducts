package com.catalog.products.controller.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"statusCode", "message"})
public class ValidationError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "status_code")
	private Integer statusCode;
	private String message;
	
	public ValidationError(Integer statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}

package com.catalog.products.controller.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({"statusCode", "message"})
@Getter
@Setter
@NoArgsConstructor
public class ValidationError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "status_code")
	private Integer statusCode;
	private String message;
	
	public ValidationError(Integer statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
}

package com.catalog.products.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@NotBlank
	private String name;
	@NotBlank
	private String description;

	@NotNull
	@Positive
	@Digits(integer = 2, fraction = 2)
	private Double price;
}

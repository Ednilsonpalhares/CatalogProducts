package com.catalog.products.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.catalog.products.domains.Product;

public class ProductDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@NotBlank(message = "campo vazio")
	private String name;
	@NotBlank
	private String description;

	@NotNull
	@Positive
	@Digits(integer=2, fraction=2)
	private BigDecimal price;
	
	public ProductDTO() {}

	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}

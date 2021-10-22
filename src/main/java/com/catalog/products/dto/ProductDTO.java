package com.catalog.products.dto;

import com.catalog.products.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
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

	public ProductDTO(String id, String name, String description, Double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public static ProductDTO toProductDTO(Product product){
		return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
	}

	public static Product toProduct(ProductDTO productDTO){
		return new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(), productDTO.getPrice());
	}
}

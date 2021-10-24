package com.catalog.products.dto;

import com.catalog.products.model.Product;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

	public static ProductDTO toProductDTO(Product product){
		return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
	}

	public static Product toProduct(ProductDTO productDTO){
		return new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(), productDTO.getPrice());
	}
}

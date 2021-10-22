package com.catalog.products.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String description;
	private Double price;

	public Product(String id, String name, String description, Double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
}
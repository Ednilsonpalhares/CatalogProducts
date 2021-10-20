package com.catalog.products.service;

import java.util.List;

import com.catalog.products.domain.Product;

public interface ProductService {
	
	List<Product> listAll();
	
	Product getForId(String id);
	
	Product create(Product product);
	
	Product update(Product product);
	
	void remove(String id);
}

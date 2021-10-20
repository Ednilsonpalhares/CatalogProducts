package com.catalog.products.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.products.domain.Product;
import com.catalog.products.repository.ProductRepository;
import com.catalog.products.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> listAll() {
		return this.productRepository.findAll();
	}

	@Override
	public Product getForId(String id) {
		return this.productRepository
				.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Product not exist."));
	}

	@Override
	public Product create(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		return null;
	}

	@Override
	public void remove(String id) {
	}
}

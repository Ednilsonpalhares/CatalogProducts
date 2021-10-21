package com.catalog.products.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.products.domains.Product;
import com.catalog.products.dto.ProductDTO;
import com.catalog.products.repositories.ProductRepository;
import com.catalog.products.service.exceptions.ObjectNotFoundExceptioin;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	public Product findById(String id) {
		return this.productRepository
				.findById(id)
				.orElseThrow(() -> new ObjectNotFoundExceptioin());
	}

	public Product insert(Product product) {
		product.setId(null);
		return this.productRepository.save(product);
	}

	public Product update(Product product) {
		findById(product.getId());
		return this.productRepository.save(product);
	}

	public void delete(String id) {
		findById(id);
		this.productRepository.deleteById(id);
	}
	
	public Product fromDTO(ProductDTO productDto) {
		return new Product(productDto.getName(), productDto.getDescription(), productDto.getPrice());
	}
}

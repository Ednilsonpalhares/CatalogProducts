package com.catalog.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.products.domain.Product;
import com.catalog.products.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> listAll() {
		return this.productService.listAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getForId(@PathVariable String id) {
		 Product product = this.productService.getForId(id);
		 return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return this.productService.create(product);
	}
}

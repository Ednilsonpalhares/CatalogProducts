package com.catalog.products.controlleres;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.products.domains.Product;
import com.catalog.products.dto.ProductDTO;
import com.catalog.products.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> productsDto = productService.findAll()
												.stream()
												.map(product -> new ProductDTO(product))
												.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(productsDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		 Product product = this.productService.findById(id);
		 
		 return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO productDto) {
		Product product = productService.insert(productService.fromDTO(productDto));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable String id) {
		product.setId(id);
		product = this.productService.update(product);
		 
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		this.productService.delete(id);
		 
		return ResponseEntity.ok().build();
	}
}

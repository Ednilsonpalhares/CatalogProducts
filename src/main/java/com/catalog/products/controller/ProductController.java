package com.catalog.products.controller;

import com.catalog.products.dto.ProductDTO;
import com.catalog.products.mapper.ProductMapper;
import com.catalog.products.model.Product;
import com.catalog.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "API REST Products Catalog")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	private final ProductMapper productMapper;

	@Operation(summary = "Create product")
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO productDto) {
		Product product = productService.insert(productMapper.toProduct(productDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toProductDTO(product));
	}

	@Operation(summary = "Update product")
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO productDto, @PathVariable String id) {
		productDto.setId(id);
		Product product = productService.update(productMapper.toProduct(productDto));

		return ResponseEntity.ok().body(productMapper.toProductDTO(product));
	}

	@Operation(summary = "View product")
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
		Product product = this.productService.findById(id);

		return ResponseEntity.ok().body(productMapper.toProductDTO(product));
	}

	@Operation(summary = "List products")
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> productsDto = productService.findAll()
												     .stream()
												     .map(product -> productMapper.toProductDTO(product))
												     .collect(Collectors.toList());

		return ResponseEntity.ok().body(productsDto);
	}

	@Operation(summary = "Search product")
	@GetMapping(value = "/search")
	public ResponseEntity<List<ProductDTO>> search(
			@RequestParam(required = false, value = "q") String nameOrDescription,
			@RequestParam(required = false, value = "min_price") Double minPrice,
			@RequestParam(required = false, value = "max_price") Double maxPrice) {

		List<ProductDTO> productsDto = productService.search(nameOrDescription, minPrice, maxPrice)
													 .stream()
													 .map(product -> productMapper.toProductDTO(product))
													 .collect(Collectors.toList());

		return ResponseEntity.ok().body(productsDto);
	}

	@Operation(summary = "Delete product")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		this.productService.delete(id);

		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Page product")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<ProductDTO> productsDto = productService.findPage(page, linesPerPage, orderBy, direction)
				                                     .map(product -> productMapper.toProductDTO(product));

		return ResponseEntity.ok().body(productsDto);
	}
}

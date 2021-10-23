package com.catalog.products.controller;

import com.catalog.products.dto.ProductDTO;
import com.catalog.products.model.Product;
import com.catalog.products.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "API REST Products Catalog")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value = "Cadastre product")
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO productDto) {
		Product product = productService.insert(ProductDTO.toProduct(productDto));

		return ResponseEntity.status(HttpStatus.CREATED).body(ProductDTO.toProductDTO(product));
	}

	@ApiOperation(value = "Update product")
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO productDto, @PathVariable String id) {
		productDto.setId(id);
		Product product = productService.update(ProductDTO.toProduct(productDto));

		return ResponseEntity.ok().body(ProductDTO.toProductDTO(product));
	}

	@ApiOperation(value = "View product")
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
		Product product = this.productService.findById(id);

		return ResponseEntity.ok().body(ProductDTO.toProductDTO(product));
	}

	@ApiOperation(value = "List products")
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> productsDto = productService.findAll()
												     .stream()
												     .map(product -> ProductDTO.toProductDTO(product))
												     .collect(Collectors.toList());

		return ResponseEntity.ok().body(productsDto);
	}

	@ApiOperation(value = "Search product")
	@GetMapping(value = "/search")
	public ResponseEntity<List<ProductDTO>> search(
			@RequestParam(required = false, value = "q") String nameOrDescription,
			@RequestParam(required = false, value = "min_price") Double minPrice,
			@RequestParam(required = false, value = "max_price") Double maxPrice) {

		List<ProductDTO> productsDto = productService.search(nameOrDescription, minPrice, maxPrice)
													 .stream()
													 .map(product -> ProductDTO.toProductDTO(product))
													 .collect(Collectors.toList());

		return ResponseEntity.ok().body(productsDto);
	}

	@ApiOperation(value = "Delete product")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		this.productService.delete(id);

		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Page product")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<ProductDTO> productsDto = productService.findPage(page, linesPerPage, orderBy, direction)
				                                     .map(product -> ProductDTO.toProductDTO(product));

		return ResponseEntity.ok().body(productsDto);
	}
}

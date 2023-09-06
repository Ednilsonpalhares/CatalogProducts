package com.catalog.products.service;

import com.catalog.products.config.Author;
import com.catalog.products.model.Product;
import com.catalog.products.repository.ProductRepository;
import com.catalog.products.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.javers.core.Javers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	private final Javers javers;


	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	public Product findById(String id) {
		return this.productRepository
				   .findById(id)
				   .orElseThrow(ObjectNotFoundException::new);
	}

	@Transactional
	public Product insert(Product product) {
		product.setId(null);
		Author.getInstance().setIdentifier("jose");
		return this.productRepository.save(product);
	}

	public Product update(Product product) {
		findById(product.getId());
		javers.commit("jose2", product);
		return this.productRepository.save(product);
	}

	public void delete(String id) {
		findById(id);

		this.productRepository.deleteById(id);
	}

	public Page<Product> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return productRepository.findAll(pageRequest);
	}

	public List<Product> search(String nameOrDescription, Double minPrice, Double maxPrice){
		return productRepository.findProductByNameOrDescriptionOrPriceMaxOrPriceMin(nameOrDescription, minPrice, maxPrice);
	}
}

package com.catalog.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.catalog.products.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}

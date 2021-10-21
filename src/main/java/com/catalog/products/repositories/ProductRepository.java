package com.catalog.products.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.catalog.products.domains.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}

package com.catalog.products.repository;

import com.catalog.products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String>{

    @Query( " { $or : [ " +
            "           { name : { $eq: ?0} }, " +
            "           { description : { $eq: ?0} }, " +
            "           { price : { $lte: ?1} }, " +
            "           { price : { $gte: ?2} } " +
            "         ] }")
    List<Product> findProductByNameOrDescriptionOrPriceMaxOrPriceMin(
            String nameOrDescription, Double minPrice, Double maxPrice);

}

package com.catalog.products.repository;

import com.catalog.products.model.Product;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
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

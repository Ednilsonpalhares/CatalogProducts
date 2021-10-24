package com.catalog.products.repository;

import com.catalog.products.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("test")
@PropertySource("classpath:application-test.properties")
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void createShouldPersistProduct(){
        //scenery
        Product product = createProduct();

        // action
        Product productSalvo = productRepository.save(product);

        // verification
        Assertions.assertThat(productSalvo.getId()).isNotNull();
    }

    public static Product createProduct(){
        return Product.builder()
                      .name("Notebook")
                      .description("Notebook Acer")
                      .price(111.20)
                      .build();
    }
}

package com.catalog.products.service;

import com.catalog.products.model.Product;
import com.catalog.products.repository.ProductRepository;
import com.catalog.products.service.exceptions.ObjectNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("test")
@PropertySource("classpath:application-test.properties")
public class ProductServiceTest {

    @SpyBean
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @Test
    public void shouldSalveProduct(){
        //scenery
        Product product = Product.builder()
                                 .id("1")
                                 .name("pen")
                                 .description("blue pen")
                                 .price(1.1).build();
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        //action
        Product productSalve = productService.insert(new Product());

        //verification
        Assertions.assertThat(productSalve).isNotNull();
        Assertions.assertThat(productSalve.getId()).isEqualTo("1");
        Assertions.assertThat(productSalve.getName()).isEqualTo("pen");
        Assertions.assertThat(productSalve.getDescription()).isEqualTo("blue pen");
        Assertions.assertThat(productSalve.getPrice()).isEqualTo(1.1);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowExceptionWhenNotFindProductRegisteredWithIdInformed(){
       productService.findById(Mockito.anyString());
    }
}

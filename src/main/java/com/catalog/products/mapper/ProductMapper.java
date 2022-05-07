package com.catalog.products.mapper;

import com.catalog.products.dto.ProductDTO;
import com.catalog.products.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDTO productDTO);

    ProductDTO toProductDTO(Product product);
}

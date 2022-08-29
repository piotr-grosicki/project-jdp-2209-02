package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;

import java.util.ArrayList;

public class ProductMapper {
    public Product mapperToProduct(ProductDto productDto){
        return new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getGroup(),
                new ArrayList<>());
    }
    public ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup()
        );

    }
}

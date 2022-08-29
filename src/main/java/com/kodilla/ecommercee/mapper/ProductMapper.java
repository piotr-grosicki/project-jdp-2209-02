package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ProductMapper {
    @Autowired
    GroupRepository groupRepository;
    public Product mapperToProduct(ProductDto productDto){
        Product product = new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                new ArrayList<>());
        Group group = groupRepository.findById(productDto.getGroupId()).orElse(null);
        product.setGroup(group);
        product.getGroup().getProductList().add(product);
        return product;
    }
    public ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getGroup().getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}

package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.service.GroupDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    @Autowired
    private GroupDbService groupDbService;

    public Product mapToNewProduct(final ProductDto productDto) throws GroupNotFoundException {
        return new Product(
                groupDbService.getGroupById(productDto.getGroupId()),
                productDto.getProductName(),
                productDto.getProductDescription(),
                productDto.getProductPrice()
        );
    }

    public Product mapToProduct(final ProductDto productDto) throws GroupNotFoundException {
        return new Product(
                productDto.getProductId(),
                groupDbService.getGroupById(productDto.getGroupId()),
                productDto.getProductName(),
                productDto.getProductDescription(),
                productDto.getProductPrice()
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getGroup().getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(final List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(productDto -> {
                    try {
                        return mapToProduct(productDto);
                    } catch (GroupNotFoundException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}

package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/shop/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductDbService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.getProductById(productId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addNewProduct(@RequestBody ProductDto productDto) throws GroupNotFoundException {
        Product product = productMapper.mapToNewProduct(productDto);
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws GroupNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(
                productMapper.mapToProductDto(
                        productService.updateProduct(
                                productMapper.mapToProduct(productDto)
                        )
                )
        );
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws ProductNotFoundException {

        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}
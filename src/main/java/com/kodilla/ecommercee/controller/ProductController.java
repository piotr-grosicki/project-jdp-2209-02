package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/shop/products")
@AllArgsConstructor
public class ProductController {
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        System.out.println("get all products");
        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(1L, 1L, "productName",
                "productDescription",
                new BigDecimal(1000)));
        products.add(new ProductDto(1L, 1L, "productName",
                "productDescription",
                new BigDecimal(1000)));
        return ResponseEntity.ok(products);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        System.out.println("get one product with id=" + productId);

        return ResponseEntity.ok(new ProductDto(1L, 1L, "productName",
                "productDescription",
                new BigDecimal(1000)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addNewProduct(@RequestBody ProductDto productDto) {
        System.out.println("Adding new product: "
                + productDto.getId() + " "
                + productDto.getName() + " "
                + productDto.getPrice());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct() {
        System.out.println("updating product");
        return ResponseEntity.ok(new ProductDto(1L, 1L, "productName",
                "productDescription",
                new BigDecimal(1000)));

    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        System.out.println("delete product by id=" + productId);
        return ResponseEntity.ok().build();
    }
}

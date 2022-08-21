package com.kodilla.ecommercee.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/shop/products")
@AllArgsConstructor
public class ProductController {
    @GetMapping
    public ResponseEntity<Void> getProducts() {
        System.out.println("get all products");
        return ResponseEntity.ok().build();
    }
    @GetMapping("{productId}")
    public ResponseEntity<Void> getProduct(@PathVariable Long productId) {
        System.out.println("get one product with id=" + productId);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public ResponseEntity<Void> addNewProduct(){
        System.out.println("Adding new product");
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Void> updateProduct(){
        System.out.println("updating product");
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(){
        System.out.println("delete product");
        return ResponseEntity.ok().build();
    }
}

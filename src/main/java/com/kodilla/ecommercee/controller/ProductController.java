package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping("v1/shop/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductDbService productService;
    private final ProductMapper productMapper;

    @ApiOperation(value = "list all product",
            response = Root.class,
            notes = "This method takes all products from the repository and returns them as a list. If not found, it is returned empty list.")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }

    @ApiOperation(value = "get single product",
            response = Root.class,
            notes = "This method looks for a product by id in the repository, then returns it. If not found, return BAD REQUEST (400).")
    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.getProductById(productId)));
    }

    @ApiOperation(value = "create product",
            response = Root.class,
            notes = "This method creates a product and adds it to the repository.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addNewProduct(@RequestBody ProductDto productDto) throws GroupNotFoundException {
        Product product = productMapper.mapToNewProduct(productDto);
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "update product data",
            response = Root.class,
            notes = "This method updates the fields in the product. If not found product to change, return BAD REQUEST (400).")
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

    @ApiOperation(value = "delete product",
            response = Root.class,
            notes = "This method delete the product. If not found return BAD REQUEST (400).")
    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws ProductNotFoundException {

        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}

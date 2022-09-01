package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/shop/carts")
public class CartController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
    }

    @GetMapping(value = "{cartId}")
    public List<ProductDto> getProductsFromCart(@PathVariable long cartId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        CartDto cartDto = new CartDto(cartId, 1L, 1L, productDtoList);
        return cartDto.getProductDtoList();
    }

    @PostMapping(value = "{productId}")
    public void addProductToCart(@PathVariable long productId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        CartDto cartDto = new CartDto(1L, 1L, 1L, productDtoList);
        ProductDto productDto = new ProductDto(productId, 1L, "productName", "productDescription", new BigDecimal(100));
        cartDto.getProductDtoList().add(productDto);
    }

    @DeleteMapping(value = "{productId}")
    public void deleteProductFromCart(@PathVariable long productId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto = new ProductDto(productId, 1L, "productName", "productDescription", new BigDecimal(100));
        CartDto cartDto = new CartDto(1L, 1L, 1L, productDtoList);
        cartDto.getProductDtoList().add(productDto);
        cartDto.getProductDtoList().remove(productDto);
    }

    @PostMapping(value = "createOrder/{cartId}")
    public void createOrderFromCart(@PathVariable long cartId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto = new ProductDto(1L, 1L, "productName", "productDescription", new BigDecimal(1000));
        CartDto cartDto = new CartDto(cartId, 1L, 1L, productDtoList);
        cartDto.getProductDtoList().add(productDto);
        new OrderDto(1L, 1L , 1L ,true,"status", new BigDecimal(22.10),productDtoList);
    }
}
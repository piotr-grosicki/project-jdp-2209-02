package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/shop/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartDbService cartDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException {
        return ResponseEntity.ok(cartDbService.saveCart(cartDto));
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartDbService.getProductsFromCart(cartId));
    }

    @PutMapping(value = "/addTo/{cartId}/product/{productId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable long cartId, @PathVariable long productId)
            throws ProductNotFoundException, CartNotFoundException {
        return ResponseEntity.ok(cartDbService.addProductToCart(cartId, productId));
    }

    @PutMapping(value = "/deleteProduct/{productId}/fromCart/{cartId}")
    public ResponseEntity<CartDto> removeProductFromCart(@PathVariable long cartId, @PathVariable long productId)
            throws ProductNotFoundException, CartNotFoundException {
        return ResponseEntity.ok(cartDbService.removeProductFromCart(cartId, productId));
    }

//    @PostMapping(value = "createOrder/{cartId}")
//    public ResponseEntity<OrderDto> createOrderFromCart(@PathVariable long cartId) throws CartNotFoundException {
//        return ResponseEntity.ok(cartDbService.createOrderFromCart(cartId));
//    }
}
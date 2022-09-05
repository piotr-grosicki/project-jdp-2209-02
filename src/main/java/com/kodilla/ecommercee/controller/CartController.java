package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/shop/carts")
public class CartController {

    @Autowired
    CartDbService cartService;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductDbService productService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException, OrderNotFoundException {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(cartMapper.mapToCartDtoList(carts));
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartService.getCart(cartId)));
    }

    @DeleteMapping(value = "{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable long cartId)  {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{cartId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> putProductIntoCart(@PathVariable long cartId, @RequestBody long productId)
            throws ProductNotFoundException, CartNotFoundException {
        Cart cart = cartService.getCart(cartId);
        cart.getProducts().add(productService.getProductById(productId));
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> updateCart(@RequestBody CartDto cartDto) throws UserNotFoundException, OrderNotFoundException {
        return ResponseEntity.ok(
                cartMapper.mapToCartDto(
                        cartService.saveCart(
                                cartMapper.mapToCart(cartDto)
                        )
                )
        );
    }
}

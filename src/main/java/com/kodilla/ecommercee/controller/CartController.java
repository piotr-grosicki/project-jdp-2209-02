package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.service.CartService;
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
    CartService service;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductDbService productDbService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        service.saveCart(cart);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<CartDto>> getCarts() {
        List<Cart> carts = service.getAllCarts();
        return ResponseEntity.ok(cartMapper.mapToCartDtoList(carts));
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable long cartId) throws Exception {
        return ResponseEntity.ok(cartMapper.mapToCartDto(service.getCart(cartId)));
    }

    @PutMapping(value = "{cartId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> putProductIntoCart(@PathVariable CartDto cartId,
                                                   @RequestBody Long id) throws Exception {
        Cart cart = cartMapper.mapToCart(cartId);
        cart.getProducts().add(productDbService.getProductById(id));
        service.saveCart(cart);
        return ResponseEntity.ok().build();
    }
}

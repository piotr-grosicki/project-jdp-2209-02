package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/shop/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartDbService cartDbService;
    private final CartMapper cartMapper;
    private final ProductDbService productService;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException {
        List<Product> products = new ArrayList<>();
        Cart cart = cartMapper.mapToNewCart(cartDto);
        cart.setProducts(products);
        cartDbService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getCarts() {
        List<Cart> carts = cartDbService.getAllCarts();
        return ResponseEntity.ok(cartMapper.mapToCartDtoList(carts));
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartDbService.getCart(cartId)));
    }

    @DeleteMapping(value = "{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable long cartId)  {
        cartDbService.deleteCart(cartId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/addTo/{cartId}")
    public ResponseEntity<CartDto> putProductIntoCart(@PathVariable long cartId, @RequestBody Product product)
            throws ProductNotFoundException, CartNotFoundException, GroupNotFoundException {
//        Product product = productMapper.mapToProduct(productDto);
        Cart cart = cartDbService.getCart(cartId);
        cart.getProducts().add(product);
        cartDbService.updateCart(cart);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> updateCart(@RequestBody CartDto cartDto)
            throws UserNotFoundException, CartNotFoundException {
        return ResponseEntity.ok(
                cartMapper.mapToCartDto(
                        cartDbService.updateCart(
                                cartMapper.mapToCart(cartDto)
                        )
                )
        );
    }
}


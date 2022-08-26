package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/shop/carts")
public class CartController {

    @Autowired
    CartService service;

    @Autowired
    CartMapper cartMapper;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        service.saveCart(cart);
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//    public ResponseEntity<List<CartDto>> getCarts() {
//        List<Cart> carts = cartRepository.findAll();
//        return ResponseEntity.ok(cartMapper.mapToCart(carts));
//    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable long cartId) throws Exception {
        return ResponseEntity.ok(cartMapper.mapToCartDto(service.getCart(cartId)));
    }
//
//    @PostMapping(value = "{productId}")
//    public void addProductToCart(@PathVariable long productId) {
//        List<ProductDto> productDtoList = new ArrayList<>();
//        CartDto cartDto = new CartDto(1L,1L,1L,productDtoList);
//        ProductDto productDto = new ProductDto(productId,"productName","productDescription",1000);
//        cartDto.getProductDtoList().add(productDto);
//    }
//
//    @DeleteMapping(value = "{productId}")
//    public void deleteProductFromCart(@PathVariable long productId) {
//        List<ProductDto> productDtoList = new ArrayList<>();
//        ProductDto productDto = new ProductDto(productId,"productName","productDescription",1000);
//        CartDto cartDto = new CartDto(1L,1L,1L,productDtoList);
//        cartDto.getProductDtoList().add(productDto);
//        cartDto.getProductDtoList().remove(productDto);
//    }
//
//    @PostMapping(value = "createOrder/{cartId}")
//    public void createOrderFromCart(@PathVariable long cartId) {
//        List<ProductDto> productDtoList = new ArrayList<>();
//        ProductDto productDto = new ProductDto(1L,"productName","productDescription",1000);
//        CartDto cartDto = new CartDto(cartId,1L,1L,productDtoList);
//        cartDto.getProductDtoList().add(productDto);
//
//        new OrderDto(1L, 1L , 1L ,true,new BigDecimal(22.10),productDtoList);
//    }
}

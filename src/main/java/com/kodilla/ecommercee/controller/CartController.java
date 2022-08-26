package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.repository.CartRepository;
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
    CartRepository cartRepository;


    //Pomoc naukowa
    User USER_EXAMPLE = new User(1L, "l", "m","a", false,
            new UUID(1L, 1L), LocalTime.now(), new ArrayList<>());

    Cart CART_EXAMPLE = new Cart(1, USER_EXAMPLE, new ArrayList<>());

    Order ORDER_EXAMPLE = new Order(1, USER_EXAMPLE, LocalDate.now(), false, "os",
            new BigDecimal("123"), CART_EXAMPLE);

    Group GROUP_EXAMPLE = new Group(1, "n", "d", new ArrayList<>());

    Product PRODUCT_EXAMPLE = new Product(1L, GROUP_EXAMPLE, "n", "d",
            new BigDecimal("123"), new ArrayList<>());


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        List<Product> products = new ArrayList<>();
        products.add(PRODUCT_EXAMPLE);

        Cart cart = new Cart(cartDto.getId());
        cartRepository.save(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public List<ProductDto> getProductsFromCart(@PathVariable long cartId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        CartDto cartDto = new CartDto(cartId,1L,1L,productDtoList);
        return cartDto.getProductDtoList();
    }

    @PostMapping(value = "{productId}")
    public void addProductToCart(@PathVariable long productId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        CartDto cartDto = new CartDto(1L,1L,1L,productDtoList);
        ProductDto productDto = new ProductDto(productId,"productName","productDescription",1000);
        cartDto.getProductDtoList().add(productDto);
    }

    @DeleteMapping(value = "{productId}")
    public void deleteProductFromCart(@PathVariable long productId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto = new ProductDto(productId,"productName","productDescription",1000);
        CartDto cartDto = new CartDto(1L,1L,1L,productDtoList);
        cartDto.getProductDtoList().add(productDto);
        cartDto.getProductDtoList().remove(productDto);
    }

    @PostMapping(value = "createOrder/{cartId}")
    public void createOrderFromCart(@PathVariable long cartId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto = new ProductDto(1L,"productName","productDescription",1000);
        CartDto cartDto = new CartDto(cartId,1L,1L,productDtoList);
        cartDto.getProductDtoList().add(productDto);

        new OrderDto(1L, 1L , 1L ,true,new BigDecimal(22.10),productDtoList);
    }
}

package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/shop/orders")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders(){
        return new ArrayList<>();
    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable int orderId){
        UserDto userDto = new UserDto(1L, "Login1", "mail@mail.com", "Adress 1", false, 123L);
        CartDto cartDto = new CartDto(1L, 1L, 1L, new ArrayList<>());
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto = new ProductDto(1L,"productName","productDescription",1000);
        cartDto.getProductDtoList().add(productDto);
        return new OrderDto(1L, userDto, cartDto, false, new BigDecimal(22.3), productDtoList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto){

    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        UserDto userDto = new UserDto(1L, "Login15", "mail@mail.com", "Adress 1", false, 123L);
        CartDto cartDto = new CartDto(1L, userDto.getId(), 1L, new ArrayList<>());
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto = new ProductDto(1L,"productName","productDescription",1000);
        cartDto.getProductDtoList().add(productDto);
        return new OrderDto(1L, userDto, cartDto, true, new BigDecimal(25.0), productDtoList);

    }
}
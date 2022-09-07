package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDbService orderDbService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(orderDbService.getAllOrders());
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long orderId) throws OrderNotFoundException, CartNotFoundException {
        return ResponseEntity.ok(orderDbService.getOrder(orderId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        return ResponseEntity.ok(orderDbService.createOrder(orderDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException, OrderNotFoundException {
        return ResponseEntity.ok(orderDbService.updateOrder(orderDto));
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long orderId) throws OrderNotFoundException{
        orderDbService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
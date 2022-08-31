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
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderDbService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long orderId) throws OrderNotFoundException, CartNotFoundException {
        Order order = orderDbService.getOrder(orderId);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(order));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        orderDbService.createOrder(order);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException, OrderNotFoundException {
        return ResponseEntity.ok(
                orderMapper.mapToOrderDto(
                        orderDbService.updateOrder(
                                orderMapper.mapToOrder(orderDto)
                        )
                )
        );
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long orderId) throws OrderNotFoundException{
        orderDbService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
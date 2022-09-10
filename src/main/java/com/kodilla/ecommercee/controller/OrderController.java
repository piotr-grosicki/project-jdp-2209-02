package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping("/v1/shop/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDbService orderDbService;
    private final OrderMapper orderMapper;

    @ApiOperation(value = "list all orders",
            response = Root.class,
            notes = "This method takes all order from the repository and returns them as a list. If not found, it is returned empty list.")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderDbService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @ApiOperation(value = "get order",
            response = Root.class,
            notes = "This method looks for a order by id in the repository, then returns it. If not found, return BAD REQUEST (400).")
    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long orderId) throws OrderNotFoundException, CartNotFoundException {
        Order order = orderDbService.getOrder(orderId);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(order));
    }

    @ApiOperation(value = "add new order",
            response = Root.class,
            notes = "This method creates a order and adds it to the repository.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        orderDbService.createOrder(order);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "update order data",
            response = Root.class,
            notes = "This method updates the fields in the order. If not found order, cart or user return BAD REQUEST (400).")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException{
        return ResponseEntity.ok(
                orderMapper.mapToOrderDto(
                        orderDbService.saveOrder(
                                orderMapper.mapToOrder(orderDto)
                        )
                )
        );
    }

    @ApiOperation(value = "delete order",
            response = Root.class,
            notes = "This method delete the order. If not found return BAD REQUEST (400).")
    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long orderId) throws OrderNotFoundException {
        orderDbService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
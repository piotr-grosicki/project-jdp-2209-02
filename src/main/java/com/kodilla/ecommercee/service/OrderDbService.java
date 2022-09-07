package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDbService {

    @Autowired
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        return orderMapper.mapToOrderDtoList(orderRepository.findAll());
    }

    public OrderDto getOrder(final Long orderId) throws OrderNotFoundException, CartNotFoundException{
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        return orderMapper.mapToOrderDto(order);
    }

    public OrderDto createOrder(final OrderDto orderDto) throws CartNotFoundException, UserNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        order.setOrderDate(LocalDate.now());
        order = orderRepository.save(order);
        return orderMapper.mapToOrderDto(order);
    }

    public void deleteOrder(final Long orderId) throws OrderNotFoundException {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException();
        }
    }

    public Order saveOrder(final Order order){
        return orderRepository.save(order);
    }

    public OrderDto updateOrder(final OrderDto orderDto) throws UserNotFoundException, CartNotFoundException{
        Order order = saveOrder(orderMapper.mapToOrder(orderDto));
        return orderMapper.mapToOrderDto(order);
    }

}

package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    @Autowired
    private ProductMapper productMapper;

    public Order mapToOrder(final OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        return new Order(
                orderDto.getId(),
                orderDto.getUser(),
                orderDto.getOrderDate(),
                orderDto.getIsPaid(),
                orderDto.getOrderStatus(),
                orderDto.getTotalPrice(),
                orderDto.getCart()
        );
    }

    public OrderDto mapToOrderDto(final Order order) throws CartNotFoundException {
        return new OrderDto(
                order.getId(),
                order.getUser(),
                order.getCart(),
                order.getOrderDate(),
                order.isPaid(),
                order.getOrderStatus(),
                order.getTotalPrice(),
                productMapper.mapToProductDtoList(order.getCart().getProducts())
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(order -> {
                    try {
                        return mapToOrderDto(order);
                    } catch (CartNotFoundException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}

package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.service.OrderDbService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getOrderDate(),
                orderDto.isPaid(),
                orderDto.getTotalPrice()
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                order.getOrderDate(),
                order.isPaid(),
                order.getTotalPrice()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}

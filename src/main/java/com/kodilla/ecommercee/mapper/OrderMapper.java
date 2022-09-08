package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderMapper {

    @Autowired
    private CartDbService cartDbService;
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private ProductMapper productMapper;

    public Order mapToOrder(final OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        return new Order(
                orderDto.getId(),
                userDbService.getUserById(orderDto.getUserId()),
                orderDto.getOrderDate(),
                orderDto.getIsPaid(),
                orderDto.getOrderStatus(),
                orderDto.getTotalPrice(),
                cartDbService.getCart(orderDto.getCartId())
        );
    }

    public OrderDto mapToOrderDto(final Order order) throws CartNotFoundException {
        List<Product> products = cartDbService.getCart(order.getCart().getId()).getProducts();
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                order.getCart().getId(),
                order.getOrderDate(),
                order.isPaid(),
                order.getOrderStatus(),
                order.getTotalPrice(),
                productMapper.mapToProductDtoList(products)
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
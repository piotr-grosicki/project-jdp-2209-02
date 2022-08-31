package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDbService {

    @Autowired
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(final Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public Order createOrder(final Order order) {
        order.setOrderDate(LocalDate.now());
        return orderRepository.save(order);
    }

    public void deleteOrder(final Long orderId) throws OrderNotFoundException {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException();
        }
    }

    public Order updateOrder(Order order) throws OrderNotFoundException {
        Order updatedOrder = getOrder(order.getId());
        if (order.getUser() != null) {
            updatedOrder.setUser(order.getUser());
        }
        if (order.getOrderDate() != null) {
            updatedOrder.setOrderDate(order.getOrderDate());
        }
        if (order.getOrderStatus() != null) {
            updatedOrder.setOrderStatus(order.getOrderStatus());
        }
        if (order.getTotalPrice() != null) {
            updatedOrder.setTotalPrice(order.getTotalPrice());
        }
        if (order.getCart() != null) {
            updatedOrder.setCart(order.getCart());
        }
        if (order.isPaid()) {
            updatedOrder.setPaid(true);
        } else {
            updatedOrder.setPaid(false);
        }
        return orderRepository.save(updatedOrder);
    }
}

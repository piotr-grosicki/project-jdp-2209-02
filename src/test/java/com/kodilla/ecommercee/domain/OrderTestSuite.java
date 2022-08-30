package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void createOrder() {
        //Given
        Order order = new Order();
        //When
        orderRepository.save(order);
        //Then
        assertEquals(1, orderRepository.count());
        assertTrue(orderRepository.existsById(order.getId()));
        //CleanUp
        orderRepository.deleteById(order.getId());
    }

    @Test
    public void getAllOrders() {
        //Given
        Order order1 = new Order();
        Order order2 = new Order();
        //When
        orderRepository.save(order1);
        orderRepository.save(order2);
        //Then
        assertEquals(2,orderRepository.findAll().size());
        //CleanUp
        orderRepository.deleteById(order1.getId());
        orderRepository.deleteById(order2.getId());
    }

    @Test
    public void getOrderById() {
        //Given
        Order order = new Order();
        //When
        orderRepository.save(order);
        //Then
        assertTrue(orderRepository.findById(order.getId()).isPresent());
        //CleanUp
        orderRepository.deleteById(order.getId());
    }
}

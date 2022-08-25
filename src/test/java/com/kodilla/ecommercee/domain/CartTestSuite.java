package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Test
    void addCartTest() {
        // Given
        User user = new User();
        Order order = new Order();
        Cart cart = new Cart();

        cart.setUser(user);
        cart.setOrder(order);

        //When
        cartRepository.save(cart);

        //Then
        assertTrue(cartRepository.findById(cart.getId()).isPresent());

        //CleanUp
        cartRepository.deleteById(cart.getId());
    }
}
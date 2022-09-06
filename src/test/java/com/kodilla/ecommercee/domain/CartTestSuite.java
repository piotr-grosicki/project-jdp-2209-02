package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Test
    void addCartTest() {
        // Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);

        //Then
        assertEquals(1, cartRepository.findAll().size());
        assertTrue(cartRepository.findById(cart.getId()).isPresent());

        //CleanUp
        cartRepository.deleteById(cart.getId());
    }

    @Test
    void getAllCarts() {
        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        //When
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //Then
        assertEquals(2, cartRepository.findAll().size());

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    void getCartById() {
        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);

        //Then
        assertTrue(cartRepository.findById(cart.getId()).isPresent());

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    void deleteCart() {
        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);
        Long id = cart.getId();
        cartRepository.deleteById(id);

        //Then
        assertEquals(Optional.empty(), cartRepository.findById(id));
        assertEquals(0, cartRepository.findAll().size());
    }
}
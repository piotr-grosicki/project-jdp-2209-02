package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    CartRepository repository;

    public List<Cart> getAllCarts() {
        return repository.findAll();
    }

    public Cart getCart(final Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(Exception::new);
    }

    public Cart saveCart(final Cart cart) {
        return repository.save(cart);
    }

    public void deleteCart(final long id) {
        repository.deleteById(id);
    }
}

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
public class CartDbService {

    @Autowired
    CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCart(final Long id) throws CartNotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);
    }

    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(final long id) {
        cartRepository.deleteById(id);
    }

    public Cart updateCart(final Cart cart) throws CartNotFoundException {
        Cart updatedCart = getCart(cart.getId());
        if (cart.getUser() != null ) {
            updatedCart.setUser(cart.getUser());
        }
        if (cart.getOrder() != null) {
            updatedCart.setOrder(cart.getOrder());
        }
        return cartRepository.save(updatedCart);
    }
}

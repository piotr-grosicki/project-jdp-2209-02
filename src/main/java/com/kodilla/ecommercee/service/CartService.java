package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Cart getCart(final Long id) throws Exception {
        return cartRepository.findById(id)
                .orElseThrow(Exception::new);
    }

}

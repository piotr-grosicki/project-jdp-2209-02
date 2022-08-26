package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getId(),
                cart.getUser(),
                cart.getOrder());
    }

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(cartDto.getId(),
                cartDto.getUserId(),
                cartDto.getOrderId());
    }
}

package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    @Autowired
    ProductMapper productMapper;

    public Cart mapToCart(final CartDto cartDto) throws UserNotFoundException {
        return new Cart(cartDto.getUser());
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser(),
                productMapper.mapToProductDtoList(cart.getProducts())
        );
    }
}

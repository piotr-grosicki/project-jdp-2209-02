package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    @Autowired
    UserDbService userDbService;
    @Autowired
    OrderDbService orderDbService;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CartDbService cartDbService;


    public Cart mapToNewCart(final CartDto cartDto) throws UserNotFoundException {
        return new Cart(
                userDbService.getUserById(cartDto.getUserId())
        );
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                productMapper.mapToProductDtoList(cart.getProducts())
        );
    }
}

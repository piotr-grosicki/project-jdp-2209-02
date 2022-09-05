package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {


    @Autowired
    UserDbService userDbService;

    @Autowired
    OrderDbService orderDbService;

    @Autowired
    ProductMapper productMapper;

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getId(),
                cart.getUser().getId(),
                cart.getOrder().getId());
    }

    public Cart mapToCart(final CartDto cartDto) throws UserNotFoundException, OrderNotFoundException {
        return new Cart(cartDto.getId(),
                userDbService.getUserById(cartDto.getUserId()),
                orderDbService.getOrder(cartDto.getOrderId()));
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList){
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }

    public List<Cart> mapToCartList(final List<CartDto> cartDtoList) {
        return cartDtoList.stream()
                .map(cartDto -> {
                    try {
                        return mapToCart(cartDto);
                        } catch (Exception e ) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}

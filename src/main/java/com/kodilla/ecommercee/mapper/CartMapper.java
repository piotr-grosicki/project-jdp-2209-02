package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductMapper productMapper;

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getId(),
                cart.getUser().getId(),
                cart.getOrder().getId(),
                productMapper.mapToProductDtoList(cart.getProducts()));
    }

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(cartDto.getId(),
                userRepository.findById(cartDto.getUserId()),
                orderRepository.findById(cartDto.getOrderId()),
                productMapper.mapToProductList(cartDto.getProductDtoList()));
    }

    public List<CartDto> mapToCartDtoList(List<Cart> cartList){
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }

//    public List<Cart> mapToCartList(List<CartDto> cartDtoList){
//        return cartDtoList.stream()
//                .map(this::mapToCart)
//                .collect(Collectors.toList());
//    }
}

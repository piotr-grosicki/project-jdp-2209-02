package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDbService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDbService orderDbService;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    public CartDto saveCart(final CartDto cartDto) throws UserNotFoundException {
        Cart cart = cartRepository.save(cartMapper.mapToCart(cartDto));
        return cartMapper.mapToCartDto(cart);
    }

    public List<ProductDto> getProductsFromCart(final long cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        return productMapper.mapToProductDtoList(cart.getProducts());
    }

    public CartDto addProductToCart(final long cartId, final long productId) throws ProductNotFoundException, CartNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        cart.getProducts().add(product);
        updateCart(cart);
        return cartMapper.mapToCartDto(cart);
    }

    public CartDto removeProductFromCart(final long cartId, final long productId) throws ProductNotFoundException, CartNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        cart.getProducts().remove(product);
        updateCart(cart);
        return cartMapper.mapToCartDto(cart);
    }

    public OrderDto createOrderFromCart(final long cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        List<Product> productsList = cart.getProducts();
        BigDecimal totalPrice = new BigDecimal(0);
        for (Product product : productsList) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        Order order = new Order(cart.getUser(), false, OrderStatus.PROCESSING, totalPrice);
        return orderDbService.createOrder(order);
    }

    public Cart getCart(final Long id) throws CartNotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);
    }

    public void updateCart(final Cart cart) throws CartNotFoundException {
        Cart updatedCart = getCart(cart.getId());
        if (cart.getUser() != null) {
            updatedCart.setUser(cart.getUser());
        }
        cartRepository.save(updatedCart);
    }
}

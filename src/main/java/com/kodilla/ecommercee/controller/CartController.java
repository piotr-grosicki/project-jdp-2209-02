package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartDbService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/shop/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartDbService cartDbService;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    @ApiOperation(value = "create empty cart",
            response = Root.class,
            notes = "This method creates a cart and saves it to the repository.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException {
        Cart cart = cartMapper.mapToNewCart(cartDto);
        cartDbService.saveCart(cart);
        return ResponseEntity.ok("CartId: " + cart.getId());
    }

    @ApiOperation(value = "get products from cart",
            response = Root.class,
            notes = "This method gets the product from the cart, if the product is not found it returns BAD REQUEST (400).")
    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDtoList(cartDbService.getProductsFromCart(cartId)));
    }

    @ApiOperation(value = "add products to selected cart",
            response = Root.class,
            notes = "This method places the Product in the cart, if the cart cannot be found or the product returns BAD REQUEST (400).")
    @PutMapping(value = "/addTo/{cartId}/product/{productId}")
    public ResponseEntity<CartDto> addProductIntoCart(@PathVariable long cartId, @PathVariable long productId)
            throws ProductNotFoundException, CartNotFoundException {
        Cart cart = cartDbService.addProductIntoCart(cartId, productId);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @ApiOperation(value = "delete product from a cart",
            response = Root.class,
            notes = "This method removes the product from the cart if the cart cannot be found or the product returns BAD REQUEST (400).")
    @PutMapping(value = "/deleteProduct/{productId}/fromCart/{cartId}")
    public ResponseEntity<CartDto> removeProductFromCart(@PathVariable long cartId, @PathVariable long productId)
            throws ProductNotFoundException, CartNotFoundException {
        Cart cart = cartDbService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @ApiOperation(value = "create order based on the cart",
            response = Root.class,
            notes = "This method creates an order from the cart, if the cart cannot be found, returns a BAD REQUEST (400).")
    @PostMapping(value = "createOrder/{cartId}")
    public ResponseEntity<OrderDto> createOrderFromCart(@PathVariable long cartId) throws CartNotFoundException {
        Order order = cartDbService.createOrderFromCart(cartId);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(order));
    }
}

package com.kodilla.ecommercee.service;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
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

    public void saveCart(final Cart cart) {
        cartRepository.save(cart);
    }

    public List<Product> getProductsFromCart(final long cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        return cart.getProducts();
    }

    public Cart addProductIntoCart(final long cartId, final long productId) throws ProductNotFoundException, CartNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        cart.getProducts().add(product);
        updateCart(cart);
        return cart;
    }

    public Cart removeProductFromCart(final long cartId, final long productId) throws ProductNotFoundException, CartNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        cart.getProducts().remove(product);
        updateCart(cart);
        return cart;
    }

    public Order createOrderFromCart(final long cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        List<Product> productsList = cart.getProducts();
        BigDecimal totalPrice = new BigDecimal(0);
        for (Product product: productsList) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        Order order = new Order(cart.getUser(),false, OrderStatus.PROCESSING,totalPrice);
        orderDbService.createOrder(order);
        return order;
    }

    public Cart getCart(final Long id) throws CartNotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);
    }

    public void updateCart(final Cart cart) throws CartNotFoundException {
        Cart updatedCart = getCart(cart.getId());
        if (cart.getUser() != null ) {
            updatedCart.setUser(cart.getUser());
        }
        cartRepository.save(updatedCart);
    }
}
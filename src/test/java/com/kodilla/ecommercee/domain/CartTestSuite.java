package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void addCartTest() {
        // Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);

        //Then
        assertEquals(1, cartRepository.findAll().size());
        assertTrue(cartRepository.findById(cart.getId()).isPresent());

        //CleanUp
        cartRepository.deleteById(cart.getId());
    }

    @Test
    void getAllCarts() {
        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        //When
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //Then
        assertEquals(2, cartRepository.findAll().size());

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    void getCartById() {
        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);

        //Then
        assertTrue(cartRepository.findById(cart.getId()).isPresent());

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    void deleteCart() {
        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);
        Long id = cart.getId();
        cartRepository.deleteById(id);

        //Then
        assertEquals(Optional.empty(), cartRepository.findById(id));
        assertEquals(0, cartRepository.findAll().size());
    }

    @Test
    void shouldFindProductListAndUserInCart() {
        //Given
        User user = new User("login", "mail@mail.com", "city", "00-000", "street", "st.000", 15L, false, null, null);
        userRepository.save(user);

        Group group = new Group("Grupa 1", "opis_grupy");
        groupRepository.save(group);

        Product product1 = new Product(group, "Produkt1", "Opis produktu1", new BigDecimal(100));
        Product product2 = new Product(group, "Produkt2", "Opis produktu2", new BigDecimal(200));
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> productList =new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(productList);

        //When
        cartRepository.save(cart);

        //Then
        assertTrue(cart.getProducts().contains(product1));
        assertEquals(2, cart.getProducts().size());
        assertEquals(user, cart.getUser());

        cartRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
        groupRepository.deleteById(group.getId());
    }

    @Test
    void deleteProductsInCartTest() {
        //Given
        User user = new User("login", "mail@mail.com", "city", "00-000", "street", "st.000", 15L, false, null, null);
        userRepository.save(user);

        Group group = new Group("Grupa 1", "opis_grupy");
        groupRepository.save(group);

        Product product1 = new Product(group, "Produkt1", "Opis produktu1", new BigDecimal(100));
        Product product2 = new Product(group, "Produkt2", "Opis produktu2", new BigDecimal(200));
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> productList =new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(productList);

        cartRepository.save(cart);

        //When
        productList.remove(product1);

        //Then
        assertEquals(1, cart.getProducts().size());
        assertFalse(cart.getProducts().contains(product1));

        cartRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
        groupRepository.deleteById(group.getId());
    }

    @Test
    void shouldDeleteCartAndLeftUserAndProducts() {
        //Given
        User user = new User("login", "mail@mail.com", "city", "00-000", "street", "st.000", 15L, false, null, null);
        userRepository.save(user);

        Group group = new Group( "Grupa 1", "opis_grupy");
        groupRepository.save(group);

        Product product1 = new Product(group, "Produkt1", "Opis produktu1", new BigDecimal(100));
        Product product2 = new Product(group, "Produkt2", "Opis produktu2", new BigDecimal(200));
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> productList =new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(productList);
        cartRepository.save(cart);

        //When
        cartRepository.deleteById(cart.getId());

        //Then
        assertFalse(cartRepository.findById(cart.getId()).isPresent());
        assertTrue(userRepository.existsById(user.getId()));
        assertTrue(productRepository.findById(product1.getId()).isPresent());
        assertTrue(productRepository.findById(product2.getId()).isPresent());

        cartRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }
}
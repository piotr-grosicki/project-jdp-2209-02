package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void testUserSave(){
        //given
        User user = new User();

        user.setId(1L);
        user.setLogin("login");
        user.setMail("user@domain.com");
        user.setAddress("address");
        user.setBlocked(false);
        user.setOrders(new ArrayList<>());

        //when
        user = userRepository.save(user);

        //then
        assertNotNull(userRepository.findById(user.getId()));
        assertEquals(user.getLogin(), userRepository.findById(user.getId()).get().getLogin());
        assertEquals(user.getMail(), userRepository.findById(user.getId()).get().getMail());
        assertEquals(user.getAddress(), userRepository.findById(user.getId()).get().getAddress());
        assertEquals(user.isBlocked(), userRepository.findById(user.getId()).get().isBlocked());
        assertTrue(userRepository.findById(user.getId()).get().getOrders().isEmpty());

        //cleanup
        userRepository.deleteById(user.getId());
    }

    @Test
    void testUserSaveNullId(){
        //given
        User user = new User();

        user.setId(null);
        user.setLogin("login");
        user.setMail("user@domain.com");
        user.setAddress("address");
        user.setBlocked(false);
        user.setOrders(null);

        //when
        try {
            user = userRepository.save(user);
        } catch (Exception e){
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testUserSaveNullLogin(){
        //given
        User user = new User();

        user.setId(1L);
        user.setLogin(null);
        user.setMail("user@domain.com");
        user.setAddress("address");
        user.setBlocked(false);
        user.setOrders(null);

        //when
        try {
            user = userRepository.save(user);
        } catch (Exception e){
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testUserSaveNullMail(){
        //given
        User user = new User();

        user.setId(1L);
        user.setLogin("login");
        user.setMail(null);
        user.setAddress("address");
        user.setBlocked(false);
        user.setOrders(null);

        //when
        try {
            user = userRepository.save(user);
        } catch (Exception e){
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testUserSaveNullAddress(){
        //given
        User user = new User();

        user.setId(1L);
        user.setLogin("login");
        user.setMail("user@domain.com");
        user.setAddress(null);
        user.setBlocked(false);
        user.setOrders(null);

        //when
        try {
            user = userRepository.save(user);
        } catch (Exception e){
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testUserSaveWithData(){
//        //given
//        Group group = new Group("grupa1","opisgrupy1");
//        groupRepository.save(group);
//
//        Product product1 = new Product("produkt1", "opis1", BigDecimal.valueOf(12.35));
//        Product product2 = new Product("produkt2", "opis2", BigDecimal.valueOf(67.89));
//        product1.setGroup(group);
//        product2.setGroup(group);
//        product1 = productRepository.save(product1);
//        product2 = productRepository.save(product2);
//
//        User user = new User();
//        user.setId(1L);
//        user.setLogin("login");
//        user.setMail("user@domain.com");
//        user.setAddress("address");
//        user.setBlocked(false);
//        user.setOrders(new ArrayList<>());
//        user = userRepository.save(user);
//
//        Cart cart = new Cart();
//        cart.setId(1L);
//        cart.setUser(user);
//        cart.setProducts(new ArrayList<>());
//        cart.getProducts().add(product1);
//        cart.getProducts().add(product2);
//        cart = cartRepository.save(cart);
//
//        System.out.println("CART:" + cart.getProducts());
//
//        Order order = new Order();
//        order.setId(1L);
//        order.setUser(user);
//        order.setCart(cart);
//        order = orderRepository.save(order);
//
//        user.getOrders().add(order);
//        user = userRepository.save(user);
//
//        System.out.println(userRepository.findById(user.getId()).get().getOrders().get(0).getId());
        //System.out.println(userRepository.findById(user.getId()).get().getOrders().get(0).getCart().getProducts());
        //System.out.println(user.getOrders().get(0).getCart().getProducts());


        //cleanup
//        userRepository.deleteById(user.getId());
//        groupRepository.deleteById(group.getId());
//        productRepository.deleteById(product1.getId());
//        productRepository.deleteById(product2.getId());
//        cartRepository.deleteById(cart.getId());
//        orderRepository.deleteById(order.getId());
    }

}

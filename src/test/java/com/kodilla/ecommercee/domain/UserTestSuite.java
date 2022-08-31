package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolationException;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        User user = new User(0L, "testUserSave", "user@domain.com", "address", false, null);

        System.out.println("user:" + user);
        //when
        User savedUser = userRepository.save(user);
        System.out.println("user:" + savedUser);

        //then
        assertNotEquals(0,userRepository.findById(savedUser.getId()).get().getId());

        assertEquals(user.getLogin(), userRepository.findById(savedUser.getId()).get().getLogin());
        assertEquals(user.getMail(), userRepository.findById(savedUser.getId()).get().getMail());
        assertEquals(user.getAddress(), userRepository.findById(savedUser.getId()).get().getAddress());
        assertEquals(user.isBlocked(), userRepository.findById(savedUser.getId()).get().isBlocked());

        //cleanup
        userRepository.delete(savedUser);
    }

    @Test
    void testUserSaveMultiple(){
        //given
        User user1 = new User(0L, "testUserSave1", "user1@domain.com", "address1", false, null);
        User user2 = new User(0L, "testUserSave2", "user2@domain.com", "address2", false, null);
        User user3 = new User(0L, "testUserSave3", "user3@domain.com", "address3", false, null);

        //when
        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);

        System.out.println("user1:" + savedUser1);
        System.out.println("user2:" + savedUser2);
        System.out.println("user3:" + savedUser3);

        //then
        assertEquals(3,userRepository.findAll().size());

        System.out.println(savedUser1);
        System.out.println(savedUser2);
        System.out.println(savedUser3);

        //cleanup
        userRepository.delete(savedUser1);
        userRepository.delete(savedUser2);
        userRepository.delete(savedUser3);
    }

    @Test
    void testUserUpdate(){
        //given
        User user = new User(0L, "testUserUpdate", "user@domain.com", "address", false, null);

        //when
        User savedUser1 = userRepository.save(user);
        System.out.println("update1: " + savedUser1);
        savedUser1.setAddress("address2");
        User savedUser2 = userRepository.save(savedUser1);
        System.out.println("update2: " + savedUser2);

        //then
        assertEquals(1,userRepository.findAll().size());
        assertEquals(savedUser1,savedUser2);

        //cleanup
        userRepository.delete(savedUser2);
    }

    @Test
    void testUserSaveNullLogin(){
        //given
        User user = new User(0L, "login", "user@domain.com", "testUserNullLogin", false, null);
        user.setLogin(null);

        try {
            userRepository.save(user);
        } catch (Exception e){
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testUserSaveNullMail(){
        //given
        User user = new User(0L, "testUserNullMail", "user@domain.com", "address", false, null);
        user.setMail(null);

        try {
            userRepository.save(user);
        } catch (Exception e){
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testUserSaveNullAddress(){
        //given
        User user = new User(0L, "testUserNullAddress", "user@domain.com", "address", false, null);
        user.setAddress(null);

        try {
            userRepository.save(user);
        } catch (Exception e){
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testDeleteCartLeaveUser(){
        //given
        User user = new User(0L, "login", "user@domain.com", "address", false, null);
        user = userRepository.save(user);

        Cart cart = new Cart(0L, new ArrayList<>(), user);
        cart = cartRepository.save(cart);

        //when
        cartRepository.delete(cart);

        //then
        assertEquals(1,userRepository.findAll().size());

        //cleanup
        userRepository.delete(user);
    }

//    @Test
//    void testUserSaveWithData(){
//        //given
//        Group group = new Group("grupa","opisgrupy");
//        group = groupRepository.save(group);
//        System.out.println("GROUP: " + group);
//
//        Product product1 = new Product(group,"produkt1", "opis1", BigDecimal.valueOf(12.35));
//        Product product2 = new Product(group,"produkt2", "opis2", BigDecimal.valueOf(67.89));
//        product1 = productRepository.save(product1);
//        product2 = productRepository.save(product2);
//
//        System.out.println("PRODUCT1: " + product1);
//        System.out.println("PRODUCT2: " + product2);
//
//        User user = new User(0L, "login", "user@domain.com", "address", false, null);
//        user = userRepository.save(user);
//
//        Cart cart = new Cart(0L, new ArrayList<>(), user);
//        cart.getProducts().add(product1);
//        cart.getProducts().add(product2);
//        cart = cartRepository.save(cart);
//
//        System.out.println("CART:" + cart);
//        System.out.println("USER: " + user);
//
//
////        Order order = new Order();
////        order.setId(1L);
////        order.setUser(user);
////        order.setCart(cart);
////        order = orderRepository.save(order);
////
////        user.getOrders().add(order);
////        user = userRepository.save(user);
////
////        System.out.println(userRepository.findById(user.getId()).get().getOrders().get(0).getId());
////        System.out.println(userRepository.findById(user.getId()).get().getOrders().get(0).getCart().getProducts());
////        System.out.println(user.getOrders().get(0).getCart().getProducts());
//
//
//        //cleanup
//        productRepository.delete(product1);
//        productRepository.delete(product2);
//        groupRepository.delete(group);
//        cartRepository.delete(cart);
//        userRepository.delete(user);
//
//
////        orderRepository.deleteById(order.getId());
//    }

}

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
        User user1 = new User(0L, "login1", "user1@domain.com", "address1", false, null);
        User user2 = new User(0L, "login2", "user2@domain.com", "address2", true, null);
        User user3 = new User(0L, "login3", "user3@domain.com", "address3", true, null);

        //when
        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);

        System.out.println(savedUser1);
        System.out.println(savedUser2);
        System.out.println(savedUser3);

        //then
        assertNotEquals(0,userRepository.findById(savedUser1.getId()).get().getId());
        assertNotEquals(0,userRepository.findById(savedUser2.getId()).get().getId());
        assertNotEquals(0,userRepository.findById(savedUser3.getId()).get().getId());

        assertEquals(user1.getLogin(), userRepository.findById(savedUser1.getId()).get().getLogin());
        assertEquals(user1.getMail(), userRepository.findById(savedUser1.getId()).get().getMail());
        assertEquals(user1.getAddress(), userRepository.findById(savedUser1.getId()).get().getAddress());
        assertEquals(user1.isBlocked(), userRepository.findById(savedUser1.getId()).get().isBlocked());

        assertEquals(user2.getLogin(), userRepository.findById(savedUser2.getId()).get().getLogin());
        assertEquals(user2.getMail(), userRepository.findById(savedUser2.getId()).get().getMail());
        assertEquals(user2.getAddress(), userRepository.findById(savedUser2.getId()).get().getAddress());
        assertEquals(user2.isBlocked(), userRepository.findById(savedUser2.getId()).get().isBlocked());

        assertEquals(user3.getLogin(), userRepository.findById(savedUser3.getId()).get().getLogin());
        assertEquals(user3.getMail(), userRepository.findById(savedUser3.getId()).get().getMail());
        assertEquals(user3.getAddress(), userRepository.findById(savedUser3.getId()).get().getAddress());
        assertEquals(user3.isBlocked(), userRepository.findById(savedUser3.getId()).get().isBlocked());

        //cleanup
        userRepository.deleteById(savedUser1.getId());
        userRepository.deleteById(savedUser2.getId());
        userRepository.deleteById(savedUser3.getId());
    }

//    @Test
//    void testUserSaveNullId(){
//        //given
//        User user = new User();
//
//        user.setId(null);
//        user.setLogin("login");
//        user.setMail("user@domain.com");
//        user.setAddress("address");
//        user.setBlocked(false);
//        user.setOrders(null);
//
//        //when
//        try {
//            user = userRepository.save(user);
//        } catch (Exception e){
//            assertEquals(ConstraintViolationException.class, e.getClass());
//        }
//    }
//
    @Test
    void testUserSaveNullLogin(){
        //given
        User user = new User();

        user.setId(0L);
        user.setLogin(null);
        user.setMail("user@domain.com");
        user.setAddress("address");
        user.setBlocked(false);
        user.setUserKey(null);

        System.out.println(user);

        try {
            user = userRepository.save(user);
            System.out.println(user);
        } catch (Exception e){
            System.out.println(user);
            System.out.println("Exception: " + e);
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

//    @Test
//    void testUserSaveNullMail(){
//        //given
//        User user = new User();
//
//        user.setId(1L);
//        user.setLogin("login");
//        user.setMail(null);
//        user.setAddress("address");
//        user.setBlocked(false);
//        user.setOrders(null);
//
//        //when
//        try {
//            user = userRepository.save(user);
//        } catch (Exception e){
//            assertEquals(ConstraintViolationException.class, e.getClass());
//        }
//    }
//
//    @Test
//    void testUserSaveNullAddress(){
//        //given
//        User user = new User();
//
//        user.setId(1L);
//        user.setLogin("login");
//        user.setMail("user@domain.com");
//        user.setAddress(null);
//        user.setBlocked(false);
//        user.setOrders(null);
//
//        //when
//        try {
//            user = userRepository.save(user);
//        } catch (Exception e){
//            assertEquals(ConstraintViolationException.class, e.getClass());
//        }
//    }

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

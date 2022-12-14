package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testUserSave() {
        //given
        User user = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);

        System.out.println("user:" + user);
        //when
        User savedUser = userRepository.save(user);
        System.out.println("user:" + savedUser);

        //then
        assertNotEquals(0, userRepository.findById(savedUser.getId()).get().getId());

        assertEquals(user.getLogin(), userRepository.findById(savedUser.getId()).get().getLogin());
        assertEquals(user.getMail(), userRepository.findById(savedUser.getId()).get().getMail());
        assertEquals(user.getCity(), userRepository.findById(savedUser.getId()).get().getCity());
        assertEquals(user.isBlocked(), userRepository.findById(savedUser.getId()).get().isBlocked());

        //cleanup
        userRepository.delete(savedUser);
    }

    @Test
    void testUserSaveMultiple() {
        //given
        User user1 = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);
        User user2 = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);
        User user3 = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);

        //when
        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);

        System.out.println("user1:" + savedUser1);
        System.out.println("user2:" + savedUser2);
        System.out.println("user3:" + savedUser3);

        //then
        assertTrue(userRepository.existsById(savedUser1.getId()));
        assertTrue(userRepository.existsById(savedUser2.getId()));
        assertTrue(userRepository.existsById(savedUser3.getId()));

        System.out.println(savedUser1);
        System.out.println(savedUser2);
        System.out.println(savedUser3);

        //cleanup
        userRepository.delete(savedUser1);
        userRepository.delete(savedUser2);
        userRepository.delete(savedUser3);
    }

    @Test
    void testUserOrder() {

        User user = new User(1L, "testUser", "user@user.com", "city","02-022","street","streetNumber 17A", 10, false, null);
        Cart cart = new Cart(0L, user, new ArrayList<>());
        Order order = new Order(user, cart, false, OrderStatus.IN_DELIVERY, new BigDecimal(22.11));

        long userIds = order.getUser().getId();
        String userLogin = order.getUser().getLogin();
        String userMail = order.getUser().getMail();
        String city = order.getUser().getCity();
        Boolean userIsBlocked = order.getUser().isBlocked();
        UUID userUserKey = order.getUser().getUserKey();

        assertAll(
                () -> assertEquals(1L, userIds),
                () -> assertEquals("testUser", userLogin),
                () -> assertEquals("user@user.com", userMail),
                () -> assertEquals("city", city),
                () -> assertFalse(userIsBlocked),
                () -> assertNull(userUserKey)
        );
    }

    @Test
    void testUserUpdate() {
        //given
        User user = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);

        //when
        User savedUser1 = userRepository.save(user);
        System.out.println("update1: " + savedUser1);
        savedUser1.setCity("warsaw");
        User savedUser2 = userRepository.save(savedUser1);
        System.out.println("update2: " + savedUser2);

        //then
        assertTrue(userRepository.existsById(savedUser2.getId()));
        assertEquals(savedUser1, savedUser2);

        //cleanup
        userRepository.delete(savedUser2);
    }

    @Test
    void testUserSaveNullLogin() {
        //given
        User user = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);
        user.setLogin(null);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testUserSaveNullMail() {
        //given
        User user = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);
        user.setMail(null);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testUserSaveNullAddress() {
        //given
        User user = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);
        user.setCity(null);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            assertEquals(ConstraintViolationException.class, e.getClass());
        }
    }

    @Test
    void testDeleteCartLeaveUser() {
        //given
        User user = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);
        user = userRepository.save(user);

        Cart cart = new Cart(0L, user, new ArrayList<>());
        cart = cartRepository.save(cart);

        //when
        cartRepository.delete(cart);

        //then
        assertTrue(userRepository.existsById(user.getId()));

        //cleanup
        userRepository.delete(user);
    }

    @Test
    void testDeleteOrderLeaveUser() {
        //given
        User user = new User(0L, "testUserSave", "user@domain.com", "city", "02-022", "street", "streetNumber 17A", 10, false, null);
        userRepository.save(user);
        Cart cart = new Cart(0L, user, new ArrayList<>());
        cartRepository.save(cart);
        Order order = new Order(user, cart, false, OrderStatus.PROCESSING, BigDecimal.ZERO);
        orderRepository.save(order);
        //when
        orderRepository.delete(order);
        //then
        assertTrue(userRepository.existsById(user.getId()));
        //cleanup
        cartRepository.deleteById(cart.getId());
        userRepository.deleteById(user.getId());
    }

}

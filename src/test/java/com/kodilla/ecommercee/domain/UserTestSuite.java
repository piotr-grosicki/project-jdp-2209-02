package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

//    @Autowired
//    private OrderRepository orderRepository;

    @Test
    void testCreateUser(){
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order());

        Cart cart = new Cart();

        User user = new User();
        User user2 = new User();

        user.setId(1L);
        user.setCart(cart);
        user.setLogin("login");
        user.setBlocked(false);
        user.setAddress("address");
        user.setMail("user@domain.com");
        user.setOrders(orders);

        user2.setId(1L);
        user2.setCart(cart);
        user2.setLogin("login2");
        user2.setBlocked(false);
        user2.setAddress("address2");
        user2.setMail("user2@domain.com");
        user2.setOrders(orders);

        System.out.println("#####");

        user = userRepository.save(user);
        user2 = userRepository.save(user2);

        System.out.println(user);
        System.out.println(user2);

        System.out.println(userRepository.findAll());

        System.out.println("#####");
    }


    //    @Test
//    public void createUserAllNull(){
//        User user = new User(
//                5L,
//                null,
//                null,
//                null,
//                false,
//                new UUID(0,0),
//                LocalTime.now());
//
//        userRepository.save(user);
//        //System.out.println(repository.findAll());
//
//        //System.out.println(user.getId());
//        //System.out.println(user.getLogin());
//        //System.out.println(user.getMail());
//    }
}

package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserSave(){
        //given
        User user = new User();

        user.setId(1L);
        user.setLogin("login");
        user.setMail("user@domain.com");
        user.setAddress("address");
        user.setBlocked(false);
        user.setOrders(null);

        //when
        user = userRepository.save(user);

        //then
        System.out.println("#####");
        System.out.println(user);
        System.out.println(userRepository.findById(user.getId()));
        System.out.println("#####");

        assertNotNull(userRepository.findById(user.getId()));
        assertEquals(user.getLogin(), userRepository.findById(user.getId()).get().getLogin());
        assertEquals(user.getMail(), userRepository.findById(user.getId()).get().getMail());
        assertEquals(user.getAddress(), userRepository.findById(user.getId()).get().getAddress());
        assertEquals(user.isBlocked(), userRepository.findById(user.getId()).get().isBlocked());
        assertTrue(userRepository.findById(user.getId()).get().getOrders().isEmpty());
    }

}

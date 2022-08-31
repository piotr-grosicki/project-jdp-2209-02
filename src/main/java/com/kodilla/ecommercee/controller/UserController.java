package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/shop/users")
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
    }
    @PutMapping(value = "/changeStatus/{userId}")
    public void changeUserStatus(@PathVariable long userId) {
    }

    @PutMapping(value = "/createKey/{userId}")
    public void createUserKey(@PathVariable long userId) {
    }
}

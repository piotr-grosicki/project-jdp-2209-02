package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/shop/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(value = "/changeStatus/{userId}")
    public void changeUserStatus(@PathVariable long userId) {

    }

    @PutMapping(value = "/createKey/{userId}")
    public ResponseEntity<UserDto> createUserKey(@PathVariable long userId) {
        UserDto userDto = userService.createUserKey(userId);
        return ResponseEntity.ok(userDto);
    }
}

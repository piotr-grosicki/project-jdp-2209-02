package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exceptions.UserExistsException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/shop/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws UserExistsException {
        return ResponseEntity.ok(userService.createUser(userDto));
    }
    @PutMapping(value = "/changeStatus/{userId}")
    public ResponseEntity<UserDto> changeUserStatus(@PathVariable long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.changeUserStatus(userId));
    }

    @PutMapping(value = "/createKey/{userId}")
    public ResponseEntity<UUID> createUserKey(@PathVariable long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.createUserKey(userId).getUserKey());
    }
}

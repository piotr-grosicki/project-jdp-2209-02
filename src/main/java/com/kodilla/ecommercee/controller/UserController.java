package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
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
    public ResponseEntity<UserDto> changeUserStatus(@PathVariable long userId) throws UserNotFoundException {
        UserDto userDto = userService.changeUserStatus(userId);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(value = "/createKey/{userId}")
    public ResponseEntity<UUID> createUserKey(@PathVariable long userId) throws UserNotFoundException {
        UserDto userDto = userService.createUserKey(userId);
        return ResponseEntity.ok(userDto.getUserKey());
    }
}

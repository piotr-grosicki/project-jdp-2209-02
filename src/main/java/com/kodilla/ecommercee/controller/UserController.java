package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exceptions.UserExistsException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController
@RequestMapping("/v1/shop/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDbService userDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws UserExistsException {
        return ResponseEntity.ok(userDbService.createUser(userDto));
    }
    @PutMapping(value = "/changeStatus/{userId}")
    public ResponseEntity<UserDto> changeUserStatus(@PathVariable long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userDbService.changeUserStatus(userId));
    }
    @PutMapping(value = "/createKey/{userId}")
    public ResponseEntity<UUID> createUserKey(@PathVariable long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userDbService.createUserKey(userId).getUserKey());
    }
}
package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exceptions.UserExistsException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.service.UserDbService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Root;
import java.util.UUID;

@RestController
@RequestMapping("/v1/shop/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDbService userDbService;

    @ApiOperation(value = "create user",
            response = Root.class,
            notes = "This method creates a user and adds them to the repository")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws UserExistsException {
        return ResponseEntity.ok(userDbService.createUser(userDto));
    }

    @ApiOperation(value = "change block user status",
            response = Root.class,
            notes = "This method changes the status of a blocked user, if blocked - unblock, if unblocked - block. If not found user in repository, returns BAD REQUEST (400).")
    @PutMapping(value = "/changeStatus/{userId}")
    public ResponseEntity<UserDto> changeUserStatus(@PathVariable long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userDbService.changeUserStatus(userId));
    }

    @ApiOperation(value = "generate random key",
            response = Root.class,
            notes = "this method generate random key that is valid for one hour after providing correct user data. If not found user in repository, returns BAD REQUEST (400).")
    @PutMapping(value = "/createKey", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UUID> createUserKey(@RequestBody UserDto userDto) throws UserNotFoundException {
            return ResponseEntity.ok(userDbService.createUserKey(userDto).getUserKey());
    }
}

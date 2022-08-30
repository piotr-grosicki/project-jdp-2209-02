package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getUserById(Long userId) throws UserNotFoundException {
        System.out.println("getUserById");
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public void createUser(UserDto userDto){
        User user = userMapper.mapToUser(userDto);
        user.setOrders(new ArrayList<>());
        user.setId(null);
        System.out.println(user);
        user = userRepository.save(user);
        System.out.println("New user: " + user.getLogin() + " created.");
    }

    public UserDto createUserKey(Long userId) throws UserNotFoundException {
        System.out.println("createUserId");

        UUID uuid = UUID.randomUUID();
        while (!isUserKeyFree(uuid)){
            System.out.println(uuid);
            uuid = UUID.randomUUID();
        }
        System.out.println(uuid);
        User user = getUserById(userId);
        user.setUserKey(uuid);
        //set validity time - current + 1H
        user = userRepository.save(user);

        return userMapper.mapToUserDto(user);
    }

    public boolean isUserKeyFree(UUID uuid){
        return !userRepository.findByUserKey(uuid).isPresent();
    }

    public UserDto changeUserStatus(Long userId) throws UserNotFoundException {
            User user = getUserById(userId);
            user.setBlocked(!user.isBlocked());
            userRepository.save(user);
            return userMapper.mapToUserDto(user);
    }

}

package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.UserLoginAlreadyExistsException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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

    public UserDto createUser(UserDto userDto) throws UserLoginAlreadyExistsException {
        User user = userMapper.mapToUser(userDto);

        if (isLoginTaken(userDto.getLogin())) {
            throw new UserLoginAlreadyExistsException();
        }

        user = userRepository.save(user);
        System.out.println("New user: " + user.getLogin() + " created.");
        return userMapper.mapToUserDto(user);
    }

    public UserDto createUserKey(Long userId) throws UserNotFoundException {
        System.out.println("createUserKey");

        UUID uuid = UUID.randomUUID();
        while (isUserKeyTaken(uuid)){
            uuid = UUID.randomUUID();
        }
        System.out.println("UserKey:" + uuid);
        User user = getUserById(userId);
        user.setUserKey(uuid);
        //set validity time - current + 1H

        user = userRepository.save(user);

        return userMapper.mapToUserDto(user);
    }

    public boolean isLoginTaken(String login) {
        System.out.println("isLoginTaken? " + login);
        return userRepository.findByLogin(login).isPresent();
    }

    public boolean isUserKeyTaken(UUID uuid){
        return userRepository.findByUserKey(uuid).isPresent();
    }

    public UserDto changeUserStatus(Long userId) throws UserNotFoundException {
            User user = getUserById(userId);
            user.setBlocked(!user.isBlocked());
            userRepository.save(user);
            return userMapper.mapToUserDto(user);
    }

}

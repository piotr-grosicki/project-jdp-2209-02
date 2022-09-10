package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exceptions.UserExistsException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public UserDto createUser(UserDto userDto) throws UserExistsException {
        User user = userMapper.mapToUser(userDto);
        if (isLoginTaken(userDto.getLogin())) {
            throw new UserExistsException();
        }
        user = userRepository.save(user);
        return userMapper.mapToUserDto(user);
    }

    public UserDto createUserKey(UserDto userDto) throws UserNotFoundException {
        User user = getUserById(userDto.getId());

        if (!user.getLogin().equals(userDto.getLogin())){
            throw new UserNotFoundException();
        }
        if (user.getLastLogin() != null){
            if (user.getLastLogin().plusHours(1).isAfter(LocalTime.now())) {
                return userMapper.mapToUserDto(user);
            }
        }

        UUID uuid = UUID.randomUUID();
        while (isUserKeyTaken(uuid)) {
            uuid = UUID.randomUUID();
        }
        user.setUserKey(uuid);
        user.setLastLogin(LocalTime.now());
        user = userRepository.save(user);
        return userMapper.mapToUserDto(user);
    }

    public boolean isLoginTaken(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    public boolean isUserKeyTaken(UUID uuid) {
        return userRepository.findByUserKey(uuid).isPresent();
    }

    public UserDto changeUserStatus(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setBlocked(!user.isBlocked());
        userRepository.save(user);
        return userMapper.mapToUserDto(user);
    }
}
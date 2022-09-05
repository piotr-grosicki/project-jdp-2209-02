package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getMail(),
                userDto.getCity(),
                userDto.getPostalNumber(),
                userDto.getStreet(),
                userDto.getStreetNumber(),
                userDto.getHouseNumber(),
                userDto.isBlocked(),
                userDto.getUserKey()
        );
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getMail(),
                user.getCity(),
                user.getPostalNumber(),
                user.getStreet(),
                user.getStreetNumber(),
                user.getHouseNumber(),
                user.isBlocked(),
                user.getUserKey()
        );
    }
}

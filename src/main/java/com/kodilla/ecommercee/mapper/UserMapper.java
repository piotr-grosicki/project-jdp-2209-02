package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserMapper {

    public User mapToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setMail(userDto.getMail());
        user.setAddress(userDto.getAddress());
        user.setBlocked(userDto.isBlocked());
        user.setOrders(new ArrayList<>());
        user.setUserKey(userDto.getUserKey());
        return user;
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(user.getId(),user.getLogin(),user.getMail(),user.getAddress(),user.isBlocked(),user.getUserKey());
    }

}

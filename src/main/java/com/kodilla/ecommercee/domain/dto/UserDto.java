package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;
    private String login;
    private String mail;
    private String address;
    private boolean isBlocked;
    private UUID userKey;
}
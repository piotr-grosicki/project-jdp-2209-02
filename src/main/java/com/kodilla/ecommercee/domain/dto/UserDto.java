package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;
    private String login;
    private String mail;
    private String city;
    private String postalNumber;
    private String street;
    private String streetNumber;
    private long apartmentNumber;
    private boolean isBlocked;
    private UUID userKey;
}

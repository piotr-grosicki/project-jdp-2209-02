package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "mail")
    private String mail;
    @Column(name = "address")
    private String address;
    @Column(name = "is_blocked")
    private boolean isBlocked;
}



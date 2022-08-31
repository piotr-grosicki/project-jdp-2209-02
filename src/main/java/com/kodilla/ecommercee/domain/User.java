package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@NotNull
    @Column(name = "ID", unique = true)
    private Long id;
    //@NotNull
    @Column(name = "LOGIN")
    private String login;
    //@NotNull
    @Column(name = "mail")
    private String mail;
    //@NotNull
    @Column(name = "ADDRESS")
    private String address;
    //@NotNull
    @Column(name = "IS_BLOCKED")
    private boolean isBlocked;
    @Column(name = "USER_KEY")
    private UUID userKey;
    @Column(name = "LAST_LOGIN")
    private LocalTime lastLogin;
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orders;

}




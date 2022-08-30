package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;
    @NotNull
    @Column(name = "LOGIN")
    private String login;
    @NotNull
    @Column(name = "mail")
    private String mail;
    @NotNull
    @Column(name = "ADDRESS")
    private String address;
    @NotNull
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

    public User(Long id, String login, String mail, String address, boolean isBlocked, UUID userKey) {
        this.id = id;
        this.login = login;
        this.mail = mail;
        this.address = address;
        this.isBlocked = isBlocked;
        this.userKey = userKey;
    }
}




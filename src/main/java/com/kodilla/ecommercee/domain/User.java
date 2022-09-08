package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "LOGIN")
    private String login;

    @NotNull
    @Email
    @Column(name = "MAIL")
    private String mail;

    @NotNull
    @Column(name = "CITY")
    private String city;

    @NotNull
    @Column(name = "POSTAL_NO")
    private String postalNumber;

    @NotNull
    @Column(name = "STREET")
    private String street;

    @NotNull
    @Column(name = "HOUSE_NO")
    private String streetNumber;

    @Column(name = "APARTMENT_NO")
    private long apartmentNumber;

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
            fetch = FetchType.LAZY
    )
    private List<Order> orders;
    public User(long id, String login, String mail, String city, String postalNumber, String street, String streetNumber, long apartmentNumber, boolean isBlocked, UUID userKey) {
        this.id = id;
        this.login = login;
        this.mail = mail;
        this.city = city;
        this.postalNumber=postalNumber;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.isBlocked = isBlocked;
        this.userKey = userKey;
        this.orders = new ArrayList<>();
    }
    public User(String login, String mail, String city, String postalNumber, String street, String streetNumber, long apartmentNumber, boolean isBlocked, UUID userKey, LocalTime lastLogin) {
        this.login = login;
        this.mail = mail;
        this.city = city;
        this.postalNumber = postalNumber;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.isBlocked = isBlocked;
        this.userKey = userKey;
        this.lastLogin = lastLogin;
        this.orders = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
package com.kodilla.ecommercee.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CARTS")
@AllArgsConstructor
@NoArgsConstructor
public class Carts {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "Id", unique = true)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "USERS_ID")
    private Users id_user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "ORDERS_ID")
    private Orders id_order;
}

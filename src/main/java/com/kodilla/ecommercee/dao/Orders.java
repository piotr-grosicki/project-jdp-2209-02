package com.kodilla.ecommercee.dao;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "Id", unique = true)
    private int id;

}

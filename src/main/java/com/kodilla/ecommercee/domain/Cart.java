package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "CARTS")

public class Cart {

    public Cart(long id, User user, Order order) {
        this.id = id;
        this.user = user;
        this.order = order;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "USERS_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "ORDERS_ID")
    private Order order;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "carts")
    private List<Product> products;

}

package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "CARTS")

public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "CART_ID", unique = true)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "USER_ID")
    private User user;

    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "carts")
    private List<Product> products;

    public Cart(User user) {
        this.user = user;
        this.products = new ArrayList<>();
    }
}

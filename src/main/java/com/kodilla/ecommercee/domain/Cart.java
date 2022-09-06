package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name= "USER_ID")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "carts")
    private List<Product> products;

    public Cart(User user) {
        this.user = user;
    }
}

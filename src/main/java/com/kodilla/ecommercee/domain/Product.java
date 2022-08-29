package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "PRODUCT_NAME")
    private String name;

    @NotNull
    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne

    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PRODUCTS_CARTS",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")}
    )
    private List<Cart> carts;

    public Product(String name, String description, BigDecimal price, List<Cart> carts) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.carts = carts;
    }
}

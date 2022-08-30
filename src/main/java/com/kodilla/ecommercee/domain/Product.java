package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @NotNull
    @Column(name = "PRODUCT_NAME")
    private String name;

    @NotNull
    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PRODUCTS_CARTS",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")}
    )
    private List<Cart> carts;

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(Group group, String name, String description, BigDecimal price) {
        this.group = group;
        this.name = name;
        this.description = description;
        this.price = price;
    }

}

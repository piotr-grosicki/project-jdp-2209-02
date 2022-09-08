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

    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "products")
    private List<Cart> carts;

    public Product(long id, Group group, String name, String description, BigDecimal price) {
        this.id = id;
        this.group = group;
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

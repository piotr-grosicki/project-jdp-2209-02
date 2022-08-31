package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "GROUPS")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Product> productList;

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Group(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

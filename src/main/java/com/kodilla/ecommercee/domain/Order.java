package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "userId")
    private long userId;

    @Column(name = "cartId")
    private long cartId;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @Column(name = "isPaid")
    private boolean isPaid;

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

}

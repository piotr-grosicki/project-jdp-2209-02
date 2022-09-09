package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "ORDER_DATE")
    private LocalDate orderDate;

    @Column(name = "IS_PAID")
    private boolean isPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private OrderStatus orderStatus;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "CART_ID")
    private Cart cart;

    public Order(LocalDate orderDate, boolean isPaid, OrderStatus orderStatus, BigDecimal totalPrice) {
        this.orderDate = orderDate;
        this.isPaid = isPaid;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }

    public Order(User user, Cart cart, boolean isPaid, OrderStatus orderStatus, BigDecimal totalPrice) {
        this.user = user;
        this.cart = cart;
        this.orderDate = LocalDate.now();
        this.isPaid = isPaid;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }
}

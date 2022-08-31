package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    //@NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

   // @NotNull
    @Column(name = "ORDER_DATE")
    private LocalDate orderDate;

   // @NotNull
    @Column(name = "IS_PAID")
    private boolean isPaid;

   // @NotNull
    @Column(name = "ORDER_STATUS")
    private String orderStatus;

  //  @NotNull
    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "CART_ID")
    private Cart cart;

    /*public Order(long id, User user, LocalDate orderDate, boolean isPaid, String orderStatus, BigDecimal totalPrice, Cart cart) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.isPaid = isPaid;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.cart = cart;
    }*/

    public Order(LocalDate orderDate, boolean isPaid, String orderStatus, BigDecimal totalPrice) {
        this.orderDate = orderDate;
        this.isPaid = isPaid;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }
}

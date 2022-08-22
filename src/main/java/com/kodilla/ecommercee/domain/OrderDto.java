package com.kodilla.ecommercee.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class OrderDto {

    private long id;
    private UserDto userDto;
    private CartDto cartDto;
    private LocalDate orderDate;
    private boolean isPaid;
    private BigDecimal totalPrice;

    public OrderDto(long id, UserDto userDto, CartDto cartDto, boolean isPaid, BigDecimal totalPrice) {
        this.id = id;
        this.userDto = userDto;
        this.cartDto = cartDto;
        this.orderDate = LocalDate.now();
        this.isPaid = isPaid;
        this.totalPrice = totalPrice;
    }
}

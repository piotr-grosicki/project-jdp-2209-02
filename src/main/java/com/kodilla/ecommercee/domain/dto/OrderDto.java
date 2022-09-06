package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private long id;
    private long userId;
    private long cartId;
    private LocalDate orderDate;
    private boolean isPaid;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private List<ProductDto> productDtoList;

    public OrderDto(long id, long userId, long cartId, boolean isPaid, OrderStatus orderStatus, BigDecimal totalPrice, List<ProductDto> productDtoList) {
        this.id = id;
        this.userId = userId;
        this.cartId = cartId;
        this.orderDate = LocalDate.now();
        this.isPaid = isPaid;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.productDtoList = productDtoList;
    }

    public boolean getIsPaid(){
        return isPaid;
    }
}
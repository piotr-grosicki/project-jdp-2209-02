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

    public boolean getIsPaid() {
        return isPaid;
    }
}

package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    public CartDto(long id, User userId, Order orderId) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
    }


    private long id;
    private User userId;
    private Order orderId;
    private List<ProductDto> productDtoList;

}

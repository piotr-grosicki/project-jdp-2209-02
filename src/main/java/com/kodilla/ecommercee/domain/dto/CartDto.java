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


    private Long id;
    private Long userId;
    private Long orderId;
    private List<ProductDto> productDtoList;

    public CartDto(long id, long userId, long orderId) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
    }
}

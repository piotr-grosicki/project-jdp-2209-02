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

    public CartDto(Long id) {
        this.id = id;
        this.userId = userId;
    }


    private Long id;
    private Long userId;
    private Long orderId;
    private List<ProductDto> productDtoList;

}

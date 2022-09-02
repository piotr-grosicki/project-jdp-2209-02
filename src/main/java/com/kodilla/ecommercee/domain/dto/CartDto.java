package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {


    private Long id;
    private Long userId;
    private Long orderId;
    private List<ProductDto> productDtoList;

}

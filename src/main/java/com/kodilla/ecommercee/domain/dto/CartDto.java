package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private long id;
    private User user;
    private List<ProductDto> productsList;

    public CartDto(User user) {
        this.user = user;
    }
}

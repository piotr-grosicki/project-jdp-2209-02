package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.UserDto;
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
    private UserDto userDto;
    private CartDto cartDto;
    private LocalDate orderDate;
    private boolean isPaid;
    private BigDecimal totalPrice;
    private List<ProductDto> productDtoList;

    public OrderDto(long id, UserDto userDto, CartDto cartDto,  boolean isPaid, BigDecimal totalPrice, List<ProductDto> productDtoList) {
        this.id = id;
        this.userDto = userDto;
        this.cartDto = cartDto;
        this.orderDate = LocalDate.now();
        this.isPaid = isPaid;
        this.totalPrice = totalPrice;
        this.productDtoList = productDtoList;
    }
}

package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private long productId;
    private long groupId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;

}

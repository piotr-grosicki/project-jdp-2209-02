package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private long id;
    private long groupId;
    private String name;
    private String description;
    private BigDecimal price;
}

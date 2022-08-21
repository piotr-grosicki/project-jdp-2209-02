package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private float price;
    private Group group;
}

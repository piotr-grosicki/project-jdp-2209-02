package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Long id;
    private String name;
    private String description;
    private List<ProductDto> productDtoList;

    public GroupDto(String name, String description, List<ProductDto> productDtoList) {
        this.name = name;
        this.description = description;
        this.productDtoList = productDtoList;
    }
}
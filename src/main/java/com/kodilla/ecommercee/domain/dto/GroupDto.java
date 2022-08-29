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
    private List<Long> productIdList;

    public GroupDto(String name, String description, List<Long> productIdList) {
        this.name = name;
        this.description = description;
        this.productIdList = productIdList;
    }
}
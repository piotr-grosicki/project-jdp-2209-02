package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Group {
    private Long id;
    private String name;
    private String description;
}

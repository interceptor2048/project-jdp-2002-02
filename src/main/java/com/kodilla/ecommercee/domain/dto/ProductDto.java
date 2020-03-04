package com.kodilla.ecommercee.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Long groupId;
}

package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    private Long productId;

    private String name;

    private BigDecimal price;

    private String description;

    private Long groupId;
}

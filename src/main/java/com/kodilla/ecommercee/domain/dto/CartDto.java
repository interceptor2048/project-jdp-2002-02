package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    private Long id;

    private List<OrderItemDto> orderItems = new ArrayList<>();

    private Long orderId;

}
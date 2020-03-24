package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.OrderItem;
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

    private Long userId;

    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    private Long orderId;
}

package com.kodilla.ecommercee.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Long user;

    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();
}

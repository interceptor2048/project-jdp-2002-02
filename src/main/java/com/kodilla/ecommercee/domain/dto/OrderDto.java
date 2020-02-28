package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.User;
import lombok.Data;

@Data
public class OrderDto {

    private Long id;

    private User user;
}

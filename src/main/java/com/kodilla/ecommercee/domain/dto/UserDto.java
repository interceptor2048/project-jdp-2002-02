package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Order;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private List<Order> orders = new ArrayList<>();
}

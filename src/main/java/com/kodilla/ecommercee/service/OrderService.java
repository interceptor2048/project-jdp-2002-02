package com.kodilla.ecommercee.service;
import com.kodilla.ecommercee.domain.dto.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDto save(OrderDto orderDTO);

    List<OrderDto> findAll();

    Optional<OrderDto> findOne(Long id);

    void delete(Long id);
}

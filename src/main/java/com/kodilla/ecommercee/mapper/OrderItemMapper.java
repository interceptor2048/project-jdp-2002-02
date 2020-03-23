package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.dto.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {


    public OrderItemDto toDto(final OrderItem orderItem){
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getProduct(),
                orderItem.getQuantity()
        );
    }

    public List<OrderItemDto> toDto(final List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(o ->toDto(o))
                .collect(Collectors.toList());
    }
}

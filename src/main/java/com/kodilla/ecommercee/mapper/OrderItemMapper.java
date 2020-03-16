package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.dto.OrderItemDto;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItem mapToOrderItem(final OrderItemDto orderItemDto){
        return new OrderItem(
                orderItemDto.getId(),
                orderItemDto.getQuantity(),
                orderItemDto.getProduct(),
                orderItemDto.getCart()
        );
    }

    public OrderItemDto mapToOrderItemDto(final OrderItem orderItem){
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getProduct(),
                orderItem.getCart()
        );
    }
}

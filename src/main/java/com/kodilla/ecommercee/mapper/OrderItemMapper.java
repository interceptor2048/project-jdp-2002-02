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

    @Autowired
    ProductDao productRepository;

    @Autowired
    CartDao cartRepository;

    public OrderItem mapToOrderItem(final OrderItemDto orderItemDto){
        return new OrderItem(
                orderItemDto.getId(),
                cartRepository.findCartById(orderItemDto.getCart()),
                productRepository.findById(orderItemDto.getProductId()).orElse(null),
                orderItemDto.getQuantity()
        );
    }

    public OrderItemDto mapToOrderItemDto(final OrderItem orderItem){
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getCart().getId(),
                orderItem.getProduct().getProductId(),
                orderItem.getQuantity()
        );
    }

    public List<OrderItemDto> mapToOrderItemDtoList(final List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(this::mapToOrderItemDto)
                .collect(Collectors.toList());
    }

    public List<OrderItem> mapToOrderItemList(final List<OrderItemDto> orderItemDtoListList) {
        return orderItemDtoListList.stream()
                .map(this::mapToOrderItem)
                .collect(Collectors.toList());
    }

}

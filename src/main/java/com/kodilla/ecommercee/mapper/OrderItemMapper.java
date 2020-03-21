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
                cartRepository.findCartById(orderItemDto.getCartId()),
                productRepository.findProductById(orderItemDto.getProductId()),
                orderItemDto.getQuantity()
        );
    }

    public OrderItemDto mapToOrderItemDto(final OrderItem orderItem){
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getProduct().getId(),
                orderItem.getCart().getId()
        );
    }

    public List<OrderItemDto> mapToOrderItemDtoList(final List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(o -> new OrderItemDto(o.getId(), o.getQuantity(), o.getProduct().getId(), o.getCart().getId()))
                .collect(Collectors.toList());
    }
}

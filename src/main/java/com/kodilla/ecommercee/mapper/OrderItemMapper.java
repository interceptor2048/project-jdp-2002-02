package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.dto.OrderItemDto;
import org.springframework.stereotype.Component;

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
                productRepository.findProductById(orderItemDto.getProductId()),
                orderItemDto.getQuantity()
        );
    }

    public OrderItemDto mapToOrderItemDto(final OrderItem orderItem){
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getCart().getId(),
                orderItem.getProduct().getId(),
                orderItem.getQuantity()
        );
    }

    public List<OrderItemDto> mapToOrderItemDtoList(final List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(o -> new OrderItemDto(o.getId(), o.getCart().getId(), o.getProduct().getId(), o.getQuantity()))
                .collect(Collectors.toList());
    }
}

package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    OrderDao orderDao;

    public Cart mapToCart(final CartDto cartDto){
        return new Cart(
                cartDto.getId(),
                orderItemMapper.mapToOrderItemList(cartDto.getOrderItems()),
                getOrderWithId(cartDto.getOrderId())
                );
    }

    public CartDto mapToCartDto(final Cart cart){
        return new CartDto(
                cart.getId(),
                orderItemMapper.mapToOrderItemDtoList(cart.getOrderItems()),
                getIdFromOrder(cart.getOrder())
        );
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList){
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
    private Long getIdFromOrder(Order order) {
        try {
            return order.getId();
        } catch (Exception e) {
            return null;
        }
    }
    private Order getOrderWithId(Long id) {
        if (id == null || id == 0)
            return null;
        return orderDao.findById(id).orElse(null);
    }
}

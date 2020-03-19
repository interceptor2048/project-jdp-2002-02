package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    UserDao userDao;

    @Autowired
    OrderDao orderDao;

    public Cart mapToCart(final CartDto cartDto){
        return new Cart(
                cartDto.getId(),
                userDao.findUserById(cartDto.getUserId()),
                cartDto.getOrderItems(),
                orderDao.findOrderById(cartDto.getOrderId())
                );
    }

    public CartDto mapToCartDto(final Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cart.getOrderItems(),
                cart.getOrder().getId()
        );
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList){
        return cartList.stream()
                .map(cart -> new CartDto(cart.getId(), cart.getUser().getId(), cart.getOrderItems(), cart.getOrder().getId()))
                .collect(Collectors.toList());
    }
}


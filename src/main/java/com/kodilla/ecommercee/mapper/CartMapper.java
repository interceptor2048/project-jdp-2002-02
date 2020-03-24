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

    public Cart toEntity(final CartDto cartDto){
        return new Cart(
                cartDto.getId(),
                userDao.findUserById(cartDto.getUserId()),
                cartDto.getOrderItems(),
                orderDao.findOrderById(cartDto.getOrderId())
                );
    }
    public CartDto toDto(final Cart cart){
        CartDto cartBean = new CartDto();
        if(cart.getUser() == null) {
            cartBean.setUserId(null);
        }
        else {
            cartBean.setUserId(cart.getUser().getId());
        }
        if(cart.getOrder()== null) {
            cartBean.setOrderId(null);
        }
        else {
            cartBean.setOrderId(cart.getOrder().getId());
        }
        cartBean.setId(cart.getId());
        return cartBean;
    }
    public List<CartDto> toDto(final List<Cart> cartList){
        return cartList.stream()
                .map(cart ->
                        toDto(cart))
                .collect(Collectors.toList());
    }
}

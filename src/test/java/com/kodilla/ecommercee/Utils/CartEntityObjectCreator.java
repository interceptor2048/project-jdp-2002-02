package com.kodilla.ecommercee.Utils;

import com.kodilla.ecommercee.domain.Cart;

import java.util.Arrays;

public class CartEntityObjectCreator {


    public static Cart createEntity() {
        Cart cart = Cart.builder()
                .orderItems(Arrays.asList(OrderItemEntityObjectCreator.createEntity()))
                .order(OrderEntityObjectCreator.createUpdatedEntityWithGivenId(1L))
                .user(UserEntityObjectCreator.createUpdatedEntityWithGivenId(1L))
                .build();
        return cart;
    }

    public static Cart createUpdatedEntityWithGivenId(Long id) {
        Cart cart = Cart.builder()
                .id(id)
                .orderItems(Arrays.asList(OrderItemEntityObjectCreator.createEntity()))
                .order(OrderEntityObjectCreator.createUpdatedEntityWithGivenId(1L))
                .user(UserEntityObjectCreator.createUpdatedEntityWithGivenId(1L))
                .build();
        return cart;
    }
}

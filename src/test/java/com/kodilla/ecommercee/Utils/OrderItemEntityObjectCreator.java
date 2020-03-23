package com.kodilla.ecommercee.Utils;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.OrderItem;

public class OrderItemEntityObjectCreator {

    private static int DEFAULT_QUANTITY = 1;
    private static int UPDATED_QUANTITY = 2;

    public static OrderItem createEntity() {
        return OrderItem.builder()
               .quantity(DEFAULT_QUANTITY)
                .product(ProductEntityObjectCreator.createUpdatedEntityWithGivenId(1L))
                .cart(Cart.builder().id(1L).build())
                .build();
    }

    public static OrderItem createUpdatedEntityWithGivenId(Long id) {
        return OrderItem.builder()
                .id(id)
                .quantity(DEFAULT_QUANTITY)
                .product(ProductEntityObjectCreator.createUpdatedEntityWithGivenId(1L))
                .cart(Cart.builder().id(1L).build())
                .build();
    }
}

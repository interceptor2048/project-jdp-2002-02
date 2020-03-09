package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public Cart mapToCart(final CartDto cartDto){
        return new Cart(
                cartDto.getId(),
                cartDto.getUser(),
                cartDto.getProducts()
        );
    }

    public CartDto mapToCartDto(Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getUser(),
                cart.getProducts()
        );
    }

    public List<CartDto> mapToCartDto(final List<Cart> cartList){
        return cartList.stream()
                .map(cart -> new CartDto(cart.getId(), cart.getUser(), cart.getProducts()))
                .collect(Collectors.toList());
    }
}
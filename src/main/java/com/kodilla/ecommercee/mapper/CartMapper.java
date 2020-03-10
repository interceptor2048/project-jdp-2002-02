package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    
    @Autowired
    UserDao userDao;

    public Cart mapToCart(final CartDto cartDto){
        return new Cart(
                cartDto.getId(),
                userDao.findUserById(cartDto.getUser()),
                cartDto.getOrderItems()
                );
    }

    public CartDto mapToCartDto(final Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cart.getOrderItems()
        );
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList){
        return cartList.stream()
                .map(cart -> new CartDto(cart.getId(), cart.getUser().getId(), cart.getOrderItems()))
                .collect(Collectors.toList());
    }
}

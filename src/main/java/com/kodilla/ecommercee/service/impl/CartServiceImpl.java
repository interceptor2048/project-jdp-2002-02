package com.kodilla.ecommercee.service.impl;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exception.CartExceptionNotFound;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartDao cartDao;
    private CartMapper cartMapper;

    public CartServiceImpl(CartDao cartDao, CartMapper cartMapper) {
        this.cartDao = cartDao;
        this.cartMapper = cartMapper;
    }

    public List<CartDto> getAllCarts(){
        return cartMapper.toDto(cartDao.findAll());
    }

    public CartDto getCartById(final Long id) throws CartExceptionNotFound {
        Optional<Cart> cart = cartDao.findById(id);
        if(!cart.isPresent()) {
            throw new CartExceptionNotFound(CartExceptionNotFound.ERR_CART_NOT_FOUND);
        }
        return cartMapper.toDto(cart.get());
    }

    public CartDto saveCart(final CartDto cart){
        Cart cartToBeSaved = cartMapper.toEntity(cart);
        cartToBeSaved = cartDao.save(cartToBeSaved);
        return cartMapper.toDto(cartToBeSaved);
    }

    public void deleteCartById(final Long id) throws CartExceptionNotFound {
        if(!cartDao.findById(id).isPresent()) {
            throw new CartExceptionNotFound(CartExceptionNotFound.ERR_CART_NOT_FOUND);
        }
        cartDao.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        if(!cartDao.findById(id).isPresent()) {
            return false;
        }
        return true;
    }
}

package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(final Long id){
        return cartRepository.findById(id);
    }

    public Cart saveCart(final Cart cart){
        return cartRepository.save(cart);
    }

    public void deleteCartById(final Long id){
        cartRepository.deleteById(id);
    }
}
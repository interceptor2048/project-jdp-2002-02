package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exception.CartExceptionNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    public List<CartDto> getAllCarts();

    public CartDto getCartById(Long id) throws CartExceptionNotFound;

    public CartDto saveCart(final CartDto cart);

    public void deleteCartById(final Long id) throws CartExceptionNotFound;

    public boolean exists(final Long id);
}

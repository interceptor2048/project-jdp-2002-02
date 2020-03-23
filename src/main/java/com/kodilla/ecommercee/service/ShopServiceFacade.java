package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exception.CartExceptionBadRequest;
import com.kodilla.ecommercee.exception.CartExceptionNotFound;
import com.kodilla.ecommercee.exception.NullArgumentException;
import com.kodilla.ecommercee.exception.UserException;
import com.kodilla.ecommercee.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceFacade {

    private CartService cartService;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public ShopServiceFacade(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    private void validateCartItems(List<OrderItem> orderItemList) throws CartExceptionNotFound {
        if(orderItemList == null) {
            throw new IllegalArgumentException("Passed arguments are equal null");
        }
        for (int i = 0; i < orderItemList.size(); i++) {
            Optional<Product> product = productService.getProduct(orderItemList.get(i).getProduct().getId());
            if(!product.isPresent()) {
                throw new CartExceptionNotFound(CartExceptionNotFound.ERR_PRODUCT_NOT_FOUND);
            }
        }
    }

    private void validateUser(Long userId) throws UserException, NullArgumentException {
        if(userId == null) {
            throw new NullArgumentException(NullArgumentException.ERR_ARGUMENTS_NULL);
        }
        if(!userService.findOne(userId).isPresent()) {
            throw new UserException(UserException.ERR_USER_NOT_FOUND);
        }
    }

    public CartDto addOrderItemsToExistingCart(CartDto cart) throws CartExceptionNotFound, UserException, NullArgumentException {
        validateCartItems(cart.getOrderItems());
        validateUser(cart.getUserId());

        CartDto cartSaved = cartService.saveCart(cart);

        return cartSaved;
    }

    public CartDto createNewCart(CartDto cart) throws UserException, NullArgumentException, CartExceptionBadRequest {
        if(cart.getId() != null) {
            if (cartService.exists(cart.getId())) {
                throw new CartExceptionBadRequest(CartExceptionBadRequest.ERR_CART_EXISTS);
            }
        }
        validateUser(cart.getUserId());
        CartDto saved = cartService.saveCart(cart);
        return saved;
    }
}

package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

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

    public List<Product> getProducts(final Long cartId) {
        return cartRepository.findById(cartId).get().getProducts();
    }

    public Cart addProductToCart(final Long cartId, final Long productId) throws NotFoundException {
        Cart theCart = cartRepository.findById(cartId).orElseThrow(NotFoundException::new);
        theCart.getProducts().add(productRepository.findById(productId).orElseThrow(NotFoundException::new));
        return cartRepository.save(theCart);
    }

    public Cart deleteProductFromCart(final Long cartId, final Long productId) throws NotFoundException {
        Cart theCart = cartRepository.findById(cartId).orElseThrow(NotFoundException::new);
        theCart.getProducts().remove(productRepository.findById(productId).orElseThrow(NotFoundException::new));
        return cartRepository.save(theCart);
    }

}
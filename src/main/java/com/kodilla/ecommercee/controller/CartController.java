package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/ecommercee")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    ProductService productService;

    @GetMapping(value = "getCartProducts")
    public List<ProductDto> getCartProducts(@RequestParam Long cartId) {
        return productMapper.mapToProductDtoList(cartService.getProducts(cartId));
    }

    @PostMapping(value = "createCart")
    public void createCart(@RequestBody CartDto cartDto) {
        cartService.saveCart(cartMapper.mapToCart(cartDto));
    }

    @PutMapping(value = "addProductToCart")
    public Cart addProductToCart(@RequestParam Long cartId, @RequestParam Long productId) throws NotFoundException {
       return cartService.addProductToCart(cartId, productId);
    }


    @DeleteMapping(value = "removeProductFromCart")
    public void deleteProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) throws NotFoundException{
        cartService.deleteProductFromCart(cartId, productId);
    }

}

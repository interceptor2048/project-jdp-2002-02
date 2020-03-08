package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee")
public class CartController {

    @RequestMapping(method = RequestMethod.GET, value = "carts")
    public void newCart(@RequestBody CartDto cartDto) {
    }

    @RequestMapping(method = RequestMethod.GET, value = "cartProducts")
    public List<ProductDto> getProducts(Long cartId) {
        return new ArrayList<ProductDto>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "cart")
    public void updateCart(Long cartId, ProductDto productDto) {
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromTheCart")
    public void deleteProduct(Long cartId, Long productId) {
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(Long cartId) {
    }
}

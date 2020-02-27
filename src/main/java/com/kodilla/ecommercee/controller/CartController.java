package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee/carts")
public class CartController {
    @PostMapping(path = "/carts", consumes = "application/json")
    public void newCart(@RequestBody CartDto cartDto) {
    }

    @GetMapping(path = "/carts")
    public List<ProductDto> getProducts(@RequestParam Long cartId) {
        return new ArrayList<ProductDto>();
    }

    @PutMapping(path = "/carts", consumes = "application/json")
    public void addToCart(@RequestParam Long cartId, @RequestBody ProductDto productDto) {
    }

    @DeleteMapping(path = "/carts")
    public void deleteProduct(@RequestParam Long cartId, @RequestParam Long productId) {
    }

    @PostMapping(value = "/carts")
    public void createOrder(@RequestParam Long cartId) {
    }
}

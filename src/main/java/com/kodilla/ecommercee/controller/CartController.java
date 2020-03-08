package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee/carts")
public class CartController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void newCart(@RequestBody CartDto cartDto) {
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProducts(@PathVariable("id") Long cartId) {
        return new ArrayList<ProductDto>();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCart(@PathVariable Long cartId, @RequestBody ProductDto productDto) {
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable("id") Long cartId, @PathVariable Long productId) {
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@PathVariable("id") Long cartId) {
    }
}
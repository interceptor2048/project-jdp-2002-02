package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/ecommercee")
@Transactional
public class CartController {

    @PostMapping(value = "carts", consumes = "application/json")
    public void newCart() {
    }

    @GetMapping(value = "carts", consumes = "application/json")
    public List<ProductDto> getProductsInCart(@RequestParam Long productId) {
        return new ArrayList<ProductDto>() ;
    }

    @PutMapping(value = "carts", consumes = "application/json")
    public void addToCart(@RequestParam Long cartId, @RequestBody ProductDto productDto) {
    }

    @DeleteMapping(value = "carts", consumes = "application/json")
    public void deleteFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
    }

    @PostMapping(value = "createOrder", consumes = "application/json")
    public void createOrder(@RequestParam ProductDto productDto) {
    }

}

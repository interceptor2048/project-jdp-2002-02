package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("v1/ecommercee")
@Transactional
public class CartController {

    @RequestMapping(method = RequestMethod.POST, value = "newCart", consumes = "application/json")
    public void newCart() {
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsInCart")
    public List<ProductDto> getProductsInCart(@RequestParam Long productId) {
        return new ArrayList<ProductDto>() ;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addToCart", consumes = "application/json")
    public void addToCart(@RequestParam Long cartId, @RequestBody ProductDto productDto) {
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteFromCart")
    public void deleteFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(@RequestParam ProductDto productDto) {
    }

}

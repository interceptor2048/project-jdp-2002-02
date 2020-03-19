package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderItemService;
import com.kodilla.ecommercee.service.ProductService;
import com.kodilla.ecommercee.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/ecommercee/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderServiceImpl orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void newCart(@RequestBody CartDto cartDto) {
        cartService.saveCart(cartMapper.mapToCart(cartDto));
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto getProductsFromCart(@PathVariable("id") Long cartId) {
        return cartMapper.mapToCartDto(cartService.getCart(cartId));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto addProductToCart(@PathVariable("id") Long cartId, @RequestBody Long productId, @RequestParam int quantity) {
        cartService.getCart(cartId).getOrderItems().add(new OrderItem(cartService.getCart(cartId), productService.getProductById(productId), quantity));
        return cartMapper.mapToCartDto(cartService.saveCart(cartService.getCart(cartId)));
    }

    @DeleteMapping(path = "/{cart_id}&{product_id}")
    public void deleteProduct(@PathVariable("cart_id") Long cartId, @PathVariable("product_id") Long productId) {
        orderItemService.deleteByCartAndProduct(cartService.getCart(cartId), productService.getProductById(productId));
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        return orderMapper.toDto(orderService.saveOrder(orderMapper.toEntity(orderDto)));
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CartDto> getAllCarts() {
        return cartMapper.mapToCartDtoList(cartService.getAllCarts());
    }

}

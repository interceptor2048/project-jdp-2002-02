package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    ProductMapper productMapper;

    @Autowired
    OrderDao orderRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void newCart(@RequestBody CartDto cartDto) {
        cartService.saveCart(cartMapper.mapToCart(cartDto));
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProducts(@PathVariable("id") Long cartId)
            throws NotFoundException {
        List<ProductDto> productDtos = new ArrayList<>();
        cartService.getCartById(cartId).orElseThrow(NotFoundException::new)
                .getOrderItems()
                .forEach(orderItem -> productDtos.add(productMapper.mapToProductDto(orderItem.getProduct())));
        return productDtos;
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@PathVariable("id") Long cartId, @RequestBody ProductDto productDto)
            throws NotFoundException {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productMapper.mapToProduct(productDto));
        cartService.getCartById(cartId).orElseThrow(NotFoundException::new)
                .getOrderItems().add(orderItem);
    }

    @DeleteMapping(path = "/{cart_id}&{product_id}")
    public void deleteProduct(@PathVariable("cart_id") Long cartId, @PathVariable("product_id") Long productId)
            throws NotFoundException {
        cartService.getCartById(cartId).orElseThrow(NotFoundException::new)
                .getOrderItems()
                .removeIf(orderItem -> orderItem
                        .getProduct()
                        .getId()
                        .equals(productId));
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@PathVariable("id") Long cartId) throws NotFoundException{
        Order order = new Order();
        Optional<Cart> cart = cartService.getCartById(cartId);
        order.setCart(cart.orElseThrow(NotFoundException::new));
        orderRepository.save(order);
    }
}

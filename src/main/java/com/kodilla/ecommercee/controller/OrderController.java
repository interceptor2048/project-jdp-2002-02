package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("api/v1/ecommercee")
public class OrderController {

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrderById(@PathVariable("id") Long id) {
        return new OrderDto();
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrderById(@PathVariable("id") Long id) {

    }

    @PutMapping("/orders/{id}")
    public OrderDto updateOrderById(@PathVariable("id") Long id) {
        return new OrderDto();
    }

    @PostMapping(path = "/orders", consumes=APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {

    }
}

package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/getOrders")
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping("/getOrderById/{id}")
    public OrderDto getOrderById(@PathVariable("id") Long id) {
        return new OrderDto();
    }

    @DeleteMapping("/deleteOrderById/{id}")
    public void deleteOrderById(@PathVariable("id") Long id) {

    }

    @PutMapping("/updateOrderById/{id}")
    public OrderDto updateOrderById(@PathVariable("id") Long id) {
        return new OrderDto();
    }

    @PostMapping(path = "/createOrder", consumes=APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {

    }
}

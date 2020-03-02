package com.kodilla.ecommercee.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.service.OrderService;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ecommercee")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private static final String ENTITY_NAME = "order";

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {
        log.debug("REST request to get all Orders");
        return orderService.findAll();
    }

    @GetMapping(value = "/orders/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes =MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        log.debug("REST request to get Order : {}", id);
        Optional<OrderDto> orderDTO = orderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderDTO);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        log.debug("REST request to delete Order : {}", id);
        orderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Ecommerce shop", false, ENTITY_NAME, id.toString())).build();
    }

    @PutMapping("/orders")
    public ResponseEntity<?> updateOrder(@RequestBody OrderDto orderDTO)  {
        log.debug("REST request to update Order : {}", orderDTO);
        if (orderDTO.getId() == null) {
            return ResponseEntity.badRequest().body("Order with given id does not exists");
        }
        OrderDto result = orderService.save(orderDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("Ecommerce shop", false, ENTITY_NAME, orderDTO.getId().toString()))
                .body(result);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDTO) throws URISyntaxException {
        log.debug("REST request to save Order : {}", orderDTO);
        if (orderDTO.getId() != null) {
            return ResponseEntity.badRequest().body("User with given id already exists");
        }
        OrderDto result = orderService.save(orderDTO);
        return ResponseEntity.created(new URI("/api/v1/ecommercee/orders/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("Ecommerce shop", false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
}

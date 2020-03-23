package com.kodilla.ecommercee.service.impl;

import com.kodilla.ecommercee.dao.OrderItemDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemDao orderItemRepository;

    public OrderItemServiceImpl(OrderItemDao orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
      return   orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }

    public List<OrderItem> getAllOrderItems() {
        return Optional.ofNullable(orderItemRepository.findAll()).orElse(new ArrayList<>());
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    public void deleteByCartAndProduct(Cart cartId, Product productId) {
        orderItemRepository.deleteByCartAndProduct(cartId, productId);
    }
}

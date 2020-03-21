package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.OrderItemDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderItemService {

    @Autowired
    private OrderItemDao orderItemRepository;

    public void createOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }

    public List<OrderItem> getAllOrderItems() {
        return Optional.ofNullable(orderItemRepository.findAll()).orElse(new ArrayList<>());
    }

    public List<OrderItem> getAllOderItemsInCart(Cart cart) {
        return orderItemRepository.findByCart(cart);
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    public void deleteByCartAndProduct(Cart cartId, Product productId) {
        orderItemRepository.deleteByCartAndProduct(cartId, productId);
    }
}

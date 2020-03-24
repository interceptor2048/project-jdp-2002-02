package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.OrderItemDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public interface OrderItemService {

    public OrderItem createOrderItem(OrderItem orderItem);

    public OrderItem getOrderItem(Long orderItemId);

    public List<OrderItem> getAllOrderItems();

    public void deleteOrderItem(Long orderItemId);

    public void deleteByCartAndProduct(Cart cartId, Product productId);
}

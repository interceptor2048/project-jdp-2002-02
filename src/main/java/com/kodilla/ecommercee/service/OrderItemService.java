package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.OrderItemDao;
import com.kodilla.ecommercee.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;

    public Optional<OrderItem> getOrderItem(final Long id){
        return orderItemDao.findById(id);
    }

    public OrderItem saveOrderItem(final OrderItem orderItem){
        return orderItemDao.save(orderItem);
    }

    public void deleteOrderItem(final Long id){
        orderItemDao.deleteById(id);
    }
}

package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface OrderItemDao extends CrudRepository<OrderItem, Long> {
}

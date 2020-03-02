package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDao extends CrudRepository<Order, Long> {

}

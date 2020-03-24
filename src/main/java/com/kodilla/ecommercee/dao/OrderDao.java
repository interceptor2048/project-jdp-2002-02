package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    List<Order> findByStatus(int status);
    Order findOrderById(Long id);
}

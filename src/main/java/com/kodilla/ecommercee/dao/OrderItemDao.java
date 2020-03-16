package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderItemDao extends CrudRepository<OrderItem, Long> {

    @Override
    Optional<OrderItem> findById(Long id);

    @Override
    List<OrderItem> findAll();

    @Override
    OrderItem save(OrderItem orderItem);

    @Override
    void deleteById(Long orderItemId);

    List<OrderItem> findByCart(Cart cart);

    void deleteByCartAndProduct(Cart cart, Product product);
}

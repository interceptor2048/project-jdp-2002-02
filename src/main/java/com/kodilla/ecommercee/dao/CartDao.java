package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CartDao extends JpaRepository<Cart, Long> {
    @Override
    List<Cart> findAll();

    Cart findCartById(Long id);
}

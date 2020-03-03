package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartDao extends CrudRepository<Cart, Long> {
}

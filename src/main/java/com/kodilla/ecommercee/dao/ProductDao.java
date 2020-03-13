package com.kodilla.ecommercee.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDao extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);

    List<Product> findAll();
}

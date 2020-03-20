package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ProductDao extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long productId);


}

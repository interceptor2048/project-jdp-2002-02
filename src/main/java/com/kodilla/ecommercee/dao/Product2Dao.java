package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Product2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface Product2Dao extends CrudRepository<Product2, Long> {
    List<Product2> findByName(String name);
}

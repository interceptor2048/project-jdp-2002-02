package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface Cart2Dao extends CrudRepository<Cart2, Long> {
}

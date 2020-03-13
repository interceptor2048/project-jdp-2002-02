package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Order2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface Order2Dao extends CrudRepository<Order2, Long> {
    List<Order2> findByStatus(int status);
}

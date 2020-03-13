package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Group2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface Group2Dao extends CrudRepository<Group2, Long> {
    List<Group2> findByName(String name);
}

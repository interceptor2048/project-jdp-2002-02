package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface Group2Dao extends CrudRepository<Group, Long> {
    List<Group> findByName(String name);
}

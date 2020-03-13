package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.User2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface User2Dao extends CrudRepository<User2, Long> {
    List<User2> findByName(String name);
}

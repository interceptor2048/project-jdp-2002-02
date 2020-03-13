package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface User2Dao extends CrudRepository<User, Long> {
    List<User> findByName(String name);
}

package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupDao extends CrudRepository<Group, Long> {
}

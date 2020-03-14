package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}

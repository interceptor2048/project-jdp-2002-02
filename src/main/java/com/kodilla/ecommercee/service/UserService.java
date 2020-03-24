package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto save(UserDto appUserDTO);

    List<UserDto> findAll();

    Optional<UserDto> findOne(Long id);

    void delete(Long id);

    void deleteAll();
}

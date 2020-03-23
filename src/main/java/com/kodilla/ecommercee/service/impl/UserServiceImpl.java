package com.kodilla.ecommercee.service.impl;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto save(UserDto user) {
        log.debug("Request to save User : {}", user);
        User userEntity = userMapper.toEntity(user);
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        log.debug("Request to get all Users");
        return userDao.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return userDao.findById(id)
                .map(userMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete User : {}", id);
        userDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete all Users : {}");
        userDao.deleteAll();
    }
}

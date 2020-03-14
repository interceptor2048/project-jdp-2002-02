package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserMapper {

    public UserDto toDto(final User user) {
        UserDto userBean = new UserDto();
        userBean.setId(user.getId());
        userBean.setUsername(user.getUsername());
        userBean.setStatus(user.getStatus());
        userBean.setUserKey(user.getUserKey());
        return userBean;
    }

    public User toEntity(final UserDto user) {
        User userBean = new User();
        userBean.setId(user.getId());
        userBean.setUsername(user.getUsername());
        userBean.setStatus(user.getStatus());
        userBean.setUserKey(user.getUserKey());
        return userBean;
    }

    public List<UserDto> toDto(final List<User> users) {
        return users.stream()
                .map(user ->
                        toDto(user))
                .collect(Collectors.toList());
    }

    public List<User> toEntity(final List<UserDto> users) {
        return users.stream()
                .map(user ->
                        toEntity(user))
                .collect(Collectors.toList());
    }
}

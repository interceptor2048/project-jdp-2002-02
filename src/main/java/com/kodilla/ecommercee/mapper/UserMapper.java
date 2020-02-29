package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;

import com.kodilla.ecommercee.domain.dto.UserDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDto, User> {

    default User fromId(Long id) {
        if (id == null) {
            return null;
        }
        User appUser = new User();
        appUser.setId(id);
        return appUser;
    }
}

package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("api/v1/ecommercee/users")
public class UserController {

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return new ArrayList<>();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return new UserDto();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {

    }

    @PutMapping("/users/{id}")
    public UserDto updateUserById(@PathVariable("id") Long id) {
        return new UserDto();
    }

    @PostMapping(path = "/users", consumes=APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDTO) {

    }
}

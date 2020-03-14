package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.service.UserService;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/ecommercee")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    private static final String ENTITY_NAME = "User";

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes =MediaType.APPLICATION_JSON_UTF8_VALUE )
    public ResponseEntity<?> getUsers() {
        log.debug("REST request to get all Users");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        log.debug("REST request to get AppUser : {}", id);
        Optional<UserDto> appUserDTO = service.findOne(id);
        return ResponseUtil.wrapOrNotFound(appUserDTO);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteAppUser(@PathVariable Long id) {
        log.debug("REST request to delete AppUser : {}", id);
        service.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Ecommerce shop", false, ENTITY_NAME, id.toString())).build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") Long id,@RequestBody UserDto userDto) throws URISyntaxException {
        log.debug("REST request to update AppUser with id : {}", id);
        if (userDto.getId() == null) {
            return ResponseEntity.badRequest().body("User with given id does not exists");
        }
        UserDto result = service.save(userDto);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("Ecommerce shop", false, ENTITY_NAME, userDto.getId().toString()))
                .body(result);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws URISyntaxException {
        log.debug("REST request to save AppUser : {}", userDto);
        if (userDto.getId() != null) {
            return ResponseEntity.badRequest().body("User with given id already exists");
        }
        UserDto result = service.save(userDto);
        return ResponseEntity.created(new URI("/api/v1/ecommercee/users/" + result.getId()))
                .body(result);
    }
}

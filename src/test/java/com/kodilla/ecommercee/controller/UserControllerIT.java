package com.kodilla.ecommercee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.service.UserService;
import com.kodilla.ecommercee.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
public class UserControllerIT {
///
    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final int DEFAULT_STATUS = 1;
    private static final int UPDATED_STATUS = 2;

    private static final Long DEFAULT_USER_KEY = 1L;
    private static final Long UPDATED_USER_KEY = 2L;

    @Autowired
    private UserMapper userMapper;

    private ObjectMapper mapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityManager em;

    private MockMvc restUserMockMvc;

    private User user;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        final UserController appUserController = new UserController(userService);
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(appUserController)
                .build();
    }

    public static User createEntity() {
        User user = User.builder()
                .userKey(DEFAULT_USER_KEY)
                .status(DEFAULT_STATUS)
                .username(DEFAULT_USER_NAME)
                .build();
        return user;
    }

    public static User createUpdatedEntity() {
        User user = User.builder()
                .username(UPDATED_USER_NAME)
                .status(UPDATED_STATUS)
                .userKey(UPDATED_USER_KEY)
                .build();

        return user;
    }

    @BeforeEach
    public void initTest() {
        user = createEntity();
    }

    @Test
    @Transactional
    public void createUser() throws Exception {
        int databaseSizeBeforeCreate = userDao.findAll().size();

        UserDto appUserDTO = userMapper.toDto(user);
        restUserMockMvc.perform(post("/api/v1/ecommercee/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(appUserDTO)))
                .andExpect(status().isCreated());

        List<User> appUserList = userDao.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeCreate + 1);
        User testUser = appUserList.get(appUserList.size() - 1);
        assertThat(testUser.getUsername()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUser.getUserKey()).isEqualTo(DEFAULT_USER_KEY);
    }

    @Test
    @Transactional
    public void createUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userDao.findAll().size();

        user.setId(1L);
        UserDto appUserDTO = userMapper.toDto(user);

        restUserMockMvc.perform(post("/api/v1/ecommercee/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(appUserDTO)))
                .andExpect(status().isBadRequest());

        List<User> appUserList = userDao.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUsers() throws Exception {
        userDao.saveAndFlush(user);

        Collection<UserDto> users = userService.findAll();

        restUserMockMvc.perform(get("/api/v1/ecommercee/users").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(mapper.writeValueAsString(users)));
    }

    @Test
    @Transactional
    public void getUser() throws Exception {
        userDao.saveAndFlush(user);

        restUserMockMvc.perform(get("/api/v1/ecommercee/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.id").value(user.getId().intValue()))
                .andExpect(jsonPath("$.username").value(DEFAULT_USER_NAME))
                .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
                .andExpect(jsonPath("$.userKey").value(DEFAULT_USER_KEY));
    }

    @Test
    @Transactional
    public void getNonExistingUser() throws Exception {
        restUserMockMvc.perform(get("/api/v1/ecommercee/users/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUser() throws Exception {
        userDao.saveAndFlush(user);

        int databaseSizeBeforeUpdate = userDao.findAll().size();

        User updatedUser = userDao.findById(user.getId()).get();
        em.detach(updatedUser);

        UserDto appUserDTO = userMapper.toDto(updatedUser);

        restUserMockMvc.perform(put("/api/v1/ecommercee/users/{id}",user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(appUserDTO)))
                .andExpect(status().isOk());

        List<User> appUserList = userDao.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
        User testUser = appUserList.get(appUserList.size() - 1);
        assertThat(testUser.getUsername()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUser.getUserKey()).isEqualTo(DEFAULT_USER_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingUser() throws Exception {
        int databaseSizeBeforeUpdate = userDao.findAll().size();

        UserDto appUserDTO = userMapper.toDto(user);

        restUserMockMvc.perform(put("/api/v1/ecommercee/users/{id}",2L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(appUserDTO)))
                .andExpect(status().isBadRequest());

        List<User> appUserList = userDao.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUser() throws Exception {
        userDao.saveAndFlush(user);

        int databaseSizeBeforeDelete = userDao.findAll().size();

        restUserMockMvc.perform(delete("/api/v1/ecommercee/users/{id}", user.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        List<User> appUserList = userDao.findAll();
        assertThat(appUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

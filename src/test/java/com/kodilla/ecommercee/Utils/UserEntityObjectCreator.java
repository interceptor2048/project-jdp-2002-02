package com.kodilla.ecommercee.Utils;

import com.kodilla.ecommercee.domain.User;

public class UserEntityObjectCreator {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final int DEFAULT_STATUS = 1;
    private static final int UPDATED_STATUS = 2;

    private static final String DEFAULT_USER_KEY = "123";
    private static final String UPDATED_USER_KEY = "1234";

    public static User createEntity() {
        User user = User.builder()
                .userKey(DEFAULT_USER_KEY)
                .status(DEFAULT_STATUS)
                .username(DEFAULT_USER_NAME)
                .build();
        return user;
    }

    public static User createUpdatedEntityWithGivenId(Long id) {
        User user = User.builder()
                .id(id)
                .username(UPDATED_USER_NAME)
                .status(UPDATED_STATUS)
                .userKey(UPDATED_USER_KEY)
                .build();

        return user;
    }
}

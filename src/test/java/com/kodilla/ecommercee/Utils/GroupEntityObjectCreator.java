package com.kodilla.ecommercee.Utils;

import com.kodilla.ecommercee.domain.Group;

import java.math.BigDecimal;
import java.util.Collections;

public class GroupEntityObjectCreator {

    private static final String DEFAULT_GROUP_NAME = "groupNameDefault";
    private static final String UPDATED_GROUP_NAME = "groupNameUpdated";

    public static Group createEntity() {
        Group group = Group.builder()
                .name(DEFAULT_GROUP_NAME)
                .build();
        return group;
    }

    public static Group createUpdatedEntity() {
        Group group = Group.builder()
                .products(Collections.singletonList(ProductEntityObjectCreator.createUpdatedEntityWithGivenId(1L)))
                .name(UPDATED_GROUP_NAME)
                .build();
        return group;
    }
}

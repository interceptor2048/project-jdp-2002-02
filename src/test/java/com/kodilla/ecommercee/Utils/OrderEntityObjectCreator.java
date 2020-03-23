package com.kodilla.ecommercee.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kodilla.ecommercee.domain.Order;

import java.time.LocalDate;

public class OrderEntityObjectCreator {

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    private static String DEFAULT_NUMBER = "123";

    private static LocalDate DEFAULT_ORDER_DATE = LocalDate.now();

    private static LocalDate DEFAULT_REQUIRED_DATE = LocalDate.now().plusDays(1L);

    private static LocalDate DEFAULT_SHIPPED_DATE = LocalDate.now().plusDays(2L);

    private static String DEFAULT_COMMENTS = "Default comment";

    private static int DEFAULT_STATUS = 1;

    private static String UPDATED_NUMBER = "123";

    private static LocalDate UPDATED_ORDER_DATE = LocalDate.now().plusDays(1L);

    private static LocalDate UPDATED_REQUIRED_DATE = LocalDate.now().plusDays(10L);

    private static LocalDate UPDATED_SHIPPED_DATE = LocalDate.now().plusDays(20L);

    private static String UPDATED_COMMENTS = "Updated comment";

    private static int UPDATED_STATUS = 2;

    public static Order createEntity() {
        return Order.builder()
                .number(DEFAULT_NUMBER)
                .requiredDate(DEFAULT_REQUIRED_DATE)
                .shippedDate(DEFAULT_SHIPPED_DATE)
                .orderDate(DEFAULT_ORDER_DATE)
                .comments(DEFAULT_COMMENTS)
                .status(DEFAULT_STATUS)
                .build();
    }

    public static Order createUpdatedEntityWithGivenId(Long id) {
        return Order.builder()
                .id(id)
                .number(UPDATED_NUMBER)
                .requiredDate(UPDATED_REQUIRED_DATE)
                .shippedDate(UPDATED_SHIPPED_DATE)
                .orderDate(UPDATED_ORDER_DATE)
                .comments(UPDATED_COMMENTS)
                .status(UPDATED_STATUS)
                .status(1)
                .build();
    }
}

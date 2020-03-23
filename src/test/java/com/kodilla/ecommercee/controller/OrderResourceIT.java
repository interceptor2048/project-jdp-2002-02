package com.kodilla.ecommercee.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kodilla.ecommercee.Utils.OrderEntityObjectCreator;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;

import com.kodilla.ecommercee.service.OrderService;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class OrderResourceIT {

    @Autowired
    private OrderDao orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderMockMvc;

    private Order order;

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @BeforeEach
    public void setup() {
        objectMapper = createObjectMapper();
        MockitoAnnotations.initMocks(this);
        final OrderController orderResource = new OrderController(orderService);
        this.restOrderMockMvc = MockMvcBuilders.standaloneSetup(orderResource)
                .build();
    }

    @BeforeEach
    public void initTest() {
        order = OrderEntityObjectCreator.createEntity();
    }

    @Test
    @Transactional
    public void createOrder() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order
        OrderDto orderDTO = orderMapper.toDto(order);
        restOrderMockMvc.perform(post("/api/v1/ecommercee/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(orderDTO)))
            .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate + 1);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getNumber()).isEqualTo(orderDTO.getNumber());
        assertThat(testOrder.getComments()).isEqualTo(orderDTO.getComments());
        assertThat(testOrder.getRequiredDate()).isEqualTo(orderDTO.getRequiredDate());
        assertThat(testOrder.getShippedDate()).isEqualTo(orderDTO.getShippedDate());
        assertThat(testOrder.getOrderDate()).isEqualTo(orderDTO.getOrderDate());
    }

    @Test
    @Transactional
    public void createOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order with an existing ID
        order.setId(1L);
        OrderDto orderDTO = orderMapper.toDto(order);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMockMvc.perform(post("/api/v1/ecommercee/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrders() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        // Get all the orderList
        restOrderMockMvc.perform(get("/api/v1/ecommercee/orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(order.getId().intValue())))
                .andExpect(jsonPath("$.[*].number").value(hasItem(order.getNumber())))
                .andExpect(jsonPath("$.[*].requiredDate").value(hasItem(order.getRequiredDate().format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.[*].shippedDate").value(hasItem(order.getShippedDate().format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.[*].orderDate").value(hasItem(order.getOrderDate().format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.[*].comments").value(hasItem(order.getComments())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(order.getStatus())));
    }
    
    @Test
    @Transactional
    public void getOrder() throws Exception {
        // Initialize the database
         orderRepository.saveAndFlush(order);

        // Get the order
        restOrderMockMvc.perform(get("/api/v1/ecommercee/orders/{id}", order.getId())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(order)));
    }

    @Test
    @Transactional
    public void getNonExistingOrder() throws Exception {
        // Get the order
        restOrderMockMvc.perform(get("/api/v1/ecommercee/orders/{id}", Long.MAX_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Update the order
        Order orderToUpdate = orderRepository.findById(order.getId()).get();
        // Disconnect from session so that the updates on updatedOrder are not directly saved in db
        em.detach(orderToUpdate);

        orderToUpdate = OrderEntityObjectCreator.createUpdatedEntityWithGivenId(orderToUpdate.getId());

        OrderDto orderDTO = orderMapper.toDto(orderToUpdate);

        restOrderMockMvc.perform(put("/api/v1/ecommercee/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(orderToUpdate)))
            .andExpect(status().isOk());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getNumber()).isEqualTo(orderDTO.getNumber());
    }

    @Test
    @Transactional
    public void updateNonExistingOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Create the Order
        OrderDto orderDTO = orderMapper.toDto(order);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMockMvc.perform(put("/api/v1/ecommercee/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeDelete = orderRepository.findAll().size();

        // Delete the order
        restOrderMockMvc.perform(delete("/api/v1/ecommercee/orders/{id}", order.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

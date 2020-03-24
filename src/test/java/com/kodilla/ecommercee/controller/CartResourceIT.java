package com.kodilla.ecommercee.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kodilla.ecommercee.Utils.CartEntityObjectCreator;
import com.kodilla.ecommercee.Utils.OrderEntityObjectCreator;
import com.kodilla.ecommercee.Utils.ProductEntityObjectCreator;
import com.kodilla.ecommercee.Utils.UserEntityObjectCreator;
import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ShopServiceFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.kodilla.ecommercee.Utils.CartEntityObjectCreator.createUpdatedEntityWithGivenId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CartResourceIT {

    @Autowired
    private ShopServiceFacade shopServiceFacade;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartService cartService;

    ObjectMapper objectMapper;

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Autowired
    private EntityManager em;

    private MockMvc restOrderMockMvc;

    private Cart cart;

    @BeforeEach
    public void setup() {
        em.persist(UserEntityObjectCreator.createEntity());
        em.persist(ProductEntityObjectCreator.createEntity());
        em.persist(OrderEntityObjectCreator.createEntity());

        objectMapper = createObjectMapper();
        MockitoAnnotations.initMocks(this);
        final CartController cartResource = new CartController(shopServiceFacade, cartService);
        this.restOrderMockMvc = MockMvcBuilders.standaloneSetup(cartResource)
                .build();
    }

    @BeforeEach
    public void initTest() {
        cart = CartEntityObjectCreator.createEntity();
    }


    @Test
    @Transactional
    public void createCart() throws Exception {
        int databaseSizeBeforeCreate = cartDao.findAll().size();

        // Create the Order
        CartDto cartDto = cartMapper.toDto(cart);
        restOrderMockMvc.perform(post("/api/v1/ecommercee/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Cart> cartList = cartDao.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeCreate + 1);
        Cart testCart = cartList.get(cartList.size() - 1);
        assertThat(testCart.getUser().getId()).isEqualTo(cartDto.getUserId());
    }

    @Test
    @Transactional
    public void createCartWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cartDao.findAll().size();

        // Create the Order
        Cart cartChanged = cart;
        cartChanged.setId(10L);
        CartDto cartDto = cartMapper.toDto(cartChanged);
        restOrderMockMvc.perform(post("/api/v1/ecommercee/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Cart> cartList = cartDao.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeCreate + 1);
        Cart testCart = cartList.get(cartList.size() - 1);
        assertThat(testCart.getUser().getId()).isEqualTo(cartDto.getUserId());
    }

    @Test
    @Transactional
    public void getCartById() throws Exception {
        // Initialize the database
        Cart cartSaved = cartDao.saveAndFlush(cart);
        CartDto cartExpected = cartMapper.toDto(cartSaved);

        // Get the order
        restOrderMockMvc.perform(get("/api/v1/ecommercee/carts/{id}", cartSaved.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(cartExpected)));
    }

    @Test
    @Transactional
    public void getNonExistingCart() throws Exception {
        // Get the order
        restOrderMockMvc.perform(get("/api/v1/ecommercee/carts/{id}", Long.MAX_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCartOrderItemsList() throws Exception {
        // Initialize the database
        cartDao.saveAndFlush(cart);

        int databaseSizeBeforeUpdate = cartDao.findAll().size();

        // Update the cart
        Cart cartToUpdate = cartDao.findById(cart.getId()).get();
        // Disconnect from session so that the updates on updatedOrder are not directly saved in db
        em.detach(cartToUpdate);

        cartToUpdate = createUpdatedEntityWithGivenId(cartToUpdate.getId());

        CartDto cartDto = cartMapper.toDto(cartToUpdate);

        restOrderMockMvc.perform(put("/api/v1/ecommercee/carts/"+cart.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cartDto)))
            .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Cart> cartList = cartDao.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
        Cart testCart = cartList.get(cartList.size() - 1);
        assertThat(testCart.getId()).isEqualTo(cartDto.getId());
    }

    @Test
    @Transactional
    public void updateNonExistingCart() throws Exception {
        int databaseSizeBeforeUpdate = cartDao.findAll().size();

        // Create the cart
        CartDto cartDto = cartMapper.toDto(cart);
        cartDto.setId(10L);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMockMvc.perform(put("/api/v1/ecommercee/carts/"+cartDto.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cartDto)))
            .andExpect(status().isNotFound());

        // Validate the Order in the database
        List<Cart> cartsList = cartDao.findAll();
        assertThat(cartsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCart() throws Exception {
        // Initialize the database
        Cart cartSaved = cartDao.saveAndFlush(cart);
        int databaseSizeBeforeDelete = cartDao.findAll().size();

        // Delete the order
        restOrderMockMvc.perform(delete("/api/v1/ecommercee/carts/"+cartSaved.getId()))
            .andExpect(status().isNoContent());
        // Validate the database contains one less item
        List<Cart> orderList = cartDao.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

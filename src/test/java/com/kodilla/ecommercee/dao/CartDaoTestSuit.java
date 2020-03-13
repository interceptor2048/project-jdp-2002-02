package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartDaoTestSuit {
    @Autowired
    GroupDao groupDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    UserDao userDao;

    @Test
    public void testCartDaoSave() {
        User user = new User();
        user.setUsername("Piotr");
        user.setStatus(1);
        Order order = new Order();
        order.setStatus(1);
        order.setOrderDate(LocalDate.of(2017, 1, 13));
        order.setRequiredDate(LocalDate.of(2017, 1, 15));
        order.setShippedDate(LocalDate.of(2017,1,13));
        order.setComments("delivery befor 12");
        user.getOrders().add(order);
        Cart cart = new Cart();
        cart.setUser(user);
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(2);


        Group group = new Group();
        group.setName("Clothes");
        groupDao.save(group);
        Product product = new Product();
        product.setName("Jacket");
        product.setDescription("Jacket_Description");
        product.setPrice(new BigDecimal("100"));
        product.setGroupId(group);

        cartDao.save(cart);
        orderItem.setProduct(product);
        orderItem.setCart(cart);
        orderItemDao.save(orderItem);
        cart.getOrderItems().add(orderItem);

        Assert.assertNotEquals(0, cartDao.findById(cart.getId()));

        cartDao.deleteAll();
    }
}

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
public class CartDaoTestSuite {
    @Autowired
    Group2Dao group2Dao;

    @Autowired
    Product2Dao product2Dao;

    @Autowired
    Cart2Dao cart2Dao;

    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    User2Dao user2Dao;

    @Test
    public void testCartDaoSave() {
        User user = new User();
        user.setName("Piotr");
        user.setStatus("1");
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
        group2Dao.save(group);
        Product product = new Product();
        product.setName("Jacket");
        product.setDescription("Jacket_Description");
        product.setPrice(new BigDecimal("100"));
        product.setGroupId(group);

        cart2Dao.save(cart);
        orderItem.setProduct(product);
        orderItem.setCart(cart);
        orderItemDao.save(orderItem);
        cart.getOrderItems().add(orderItem);

        Assert.assertNotEquals(0, cart2Dao.findById(cart.getId()));

        cart2Dao.deleteAll();
    }
}

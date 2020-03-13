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
public class Order2TestSuite {

    @Autowired
    Order2Dao order2Dao;
    @Autowired
    Cart2Dao cart2Dao;
    @Autowired
    User2Dao user2Dao;
    @Autowired
    Group2Dao group2Dao;
    @Autowired
    Product2Dao product2Dao;
    @Autowired
    OrderItemDao orderItemDao;

    @Test
    public void testOrderDaoSave() {
        User2 user = new User2();
        Order2 order = new Order2(1,
                LocalDate.of(2017, 1, 13),
                LocalDate.of(2017, 1, 15),
                LocalDate.of(2017,1,13),
                "delivery befor 12");
        user.getOrders().add(order);
        Cart2 cart = new Cart2();
        cart.setUser(user);
        cart2Dao.save(cart);
        OrderItem orderItem = new OrderItem(2);
        orderItem.setCart(cart);
        Group2 group = new Group2("Clothes");
        group2Dao.save(group);
        Product2 product = new Product2("Jacket", "Jacket_Description", new BigDecimal("100"));
        product.setGroupId(group);
        orderItem.setProduct(product);
        orderItemDao.save(orderItem);
        cart.getOrderItems().add(orderItem);
        cart2Dao.save(cart);
        order.setUser(user);
        order.setCart(cart);
        order2Dao.save(order);
        Assert.assertEquals(1, order2Dao.findByStatus(1).size());

        order2Dao.deleteAll();
    }
}

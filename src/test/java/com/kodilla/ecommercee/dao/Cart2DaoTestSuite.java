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
public class Cart2DaoTestSuite {
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
        User2 user = new User2("Piotr", "1");
        Order2 order = new Order2(1,
                LocalDate.of(2017, 1, 13),
                LocalDate.of(2017, 1, 15),
                LocalDate.of(2017,1,13),
                "delivery befor 12");
        user.getOrders().add(order);


        Cart2 cart = new Cart2();
        cart.setUser(user);
        OrderItem orderItem = new OrderItem(2);


        Group2 group = new Group2("Clothes");
        group2Dao.save(group);
        Product2 product = new Product2("Jacket", "Jacket_Description", new BigDecimal("100"));
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

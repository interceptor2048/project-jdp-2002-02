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
public class UserDaoTestSuite {

    @Autowired
    Cart2Dao cart2Dao;
    @Autowired
    Group2Dao group2Dao;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    User2Dao user2Dao;

    @Test
    public void testUser2DaoSave() {
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
        cart2Dao.save(cart);
        OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(2);
        orderItem.setCart(cart);
        Group group = new Group();
                group.setName("Clothes");
        group2Dao.save(group);
        Product product = new Product();
                    product.setName("Jacket");
                    product.setDescription("Jacket_Description");
                    product.setPrice(new BigDecimal("100"));
                    product.setGroupId(group);
        orderItem.setProduct(product);
        orderItemDao.save(orderItem);
        cart.getOrderItems().add(orderItem);
        cart2Dao.save(cart);
        order.setUser(user);
        order.setCart(cart);
        user.getOrders().add(order);
        user2Dao.save(user);

        Assert.assertEquals(1, user2Dao.findByName("Piotr").size());
        user2Dao.deleteAll();

    }
}

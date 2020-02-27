package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTestSuite {
    @Autowired
    UserDao userDao;
    private static final String FIRST_NAME = "Carol";
    private static final String LAST_NAME = "Smith";

    @Test
    public void testUserDaoSave() {
        Order order = new Order();
             order.setStatus("inProgress");
        User user = new User();
             user.setFirstName(FIRST_NAME);
             user.setLastName(LAST_NAME);
             user.getOrders().add(order);
             order.setUser(user);
        userDao.save(user);
        List<User> listOfUsersByFirstName = userDao.findByFirstName(FIRST_NAME);
        Assert.assertEquals(1, listOfUsersByFirstName.size());
        Assert.assertEquals("inProgress", user.getOrders().get(0).getStatus());
        userDao.deleteById(user.getId());
    }
}

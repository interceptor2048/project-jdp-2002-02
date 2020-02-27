package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTestSuite {

    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;
    private static final String FIRST_NAME = "John";

    @Test
    public void testOrderDaoSave() {
        User user1 = new User();
                user1.setFirstName("John");
                user1.setLastName("Smith");
        userDao.save(user1);
        User user2 = new User();
                user2.setFirstName("Emma");
                user2.setLastName("Kowalsky");
        userDao.save(user2);


        Order order1 = new Order();
                order1.setStatus("inProgress");
                order1.setUser(user1);
        orderDao.save(order1);
        Order order2 = new Order();
                order2.setStatus("Done");
                order2.setUser(user2);
        orderDao.save(order2);
        Order order3 = new Order();
                order3.setStatus("inProgress");
                order3.setUser(user1);
        orderDao.save(order3);

        List<Order> orderList = new ArrayList<>();
        orderDao.findAll().forEach(orderList::add);
        List<Order> usersByFirstName = orderList.stream().filter(n -> n.getUser().getFirstName().equals(FIRST_NAME)).collect(Collectors.toList());
        Assert.assertEquals(2, usersByFirstName.size());
        Assert.assertEquals(2, orderDao.findByStatus("inProgress").size());
        orderDao.deleteAll();
    }
}

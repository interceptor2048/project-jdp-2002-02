package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartDaoTestSuit {

    @Autowired
    ProductDao productDao;
    @Autowired
    CartDao cartDao;
    @Autowired
    GroupDao groupDao;


    @Test
    public void testGroupDaoSave() {
        User user = new User();
        user.setFirstName("name");
        user.setLastName("lastName");
        Group group = new Group();
        group.setName("groipName");
        groupDao.save(group);
        Product product = new Product();
        product.setName("productName");
        product.setPrice(new BigDecimal("23"));
        product.setDescription("productDescription");
        Cart cart = new Cart();
        cart.setUser(user);
        product.getCarts().add(cart);
        productDao.save(product);
        cart.getProducts().add(product);
        cartDao.save(cart);

        Assert.assertTrue(cart.getProducts().stream().anyMatch(p -> p.getName().equals("productName")));
        Assert.assertNotEquals(0, cart.getId());
    }
}

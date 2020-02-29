package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartDaoTestSuit {

    @Autowired
    ProductDao productDao;
    @Autowired
    CartDao cartDao;
    @Autowired
    GroupDao groupDao;


    @Before
    public void clean() {
        productDao.deleteAll();
        groupDao.deleteAll();
        cartDao.deleteAll();
    }


    @Test
    public void testCartDaoSave() {
        Group group = new Group();
        group.setName("groipName");
        groupDao.save(group);
        Product product = new Product();
        product.setName("productName");
        product.setPrice(new BigDecimal("23"));
        product.setDescription("productDescription");
        product.setGroupId(group);
        Cart cart = new Cart();
        productDao.save(product);
        cart.getProducts().add(product);
        cartDao.save(cart);

        Assert.assertTrue(cart.getProducts().stream().anyMatch(p -> p.getName().equals("productName")));
        Assert.assertNotEquals(0L, cart.getId().longValue());
    }
}

package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTestSuite {

    @Autowired
    GroupDao groupDao;
    @Autowired
    ProductDao productDao;

    @Test
    public void testProductDaoSave() {
        Group group = new Group();
                group.setName("Clothes");
        groupDao.save(group);
        Product product = new Product();
                product.setName("Jacket");
                product.setDescription("Jacket_Description");
                product.setPrice(new BigDecimal("100"));
                product.setGroup(group);
        productDao.save(product);

        Assert.assertEquals(1, productDao.findByName("Jacket").size());
        productDao.deleteAll();
        groupDao.deleteAll();
    }

}

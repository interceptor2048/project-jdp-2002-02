package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Group2;
import com.kodilla.ecommercee.domain.Product2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Product2DaoTestSuite {

    @Autowired
    Group2Dao group2Dao;
    @Autowired
    Product2Dao product2Dao;

    @Test
    public void testProduct2DaoSave() {
        Group2 group = new Group2();
        group.setName("Clothes");
        group2Dao.save(group);
        Product2 product = new Product2();
                product.setName("Jacket");
                product.setDescription("Jacket_Description");
                product.setPrice(new BigDecimal("100"));
                product.setGroupId(group);
        product2Dao.save(product);

        Assert.assertEquals(1, product2Dao.findByName("Jacket").size());
        product2Dao.deleteAll();
        group2Dao.deleteAll();
    }

}

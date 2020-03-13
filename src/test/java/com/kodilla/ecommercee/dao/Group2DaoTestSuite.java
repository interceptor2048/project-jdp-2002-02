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
public class Group2DaoTestSuite {

    @Autowired
    Group2Dao group2Dao;
    @Autowired
    Product2Dao product2Dao;

    @Test
    public void testGroup2DaoSave() {
        Group2 group = new Group2("Food");
        Product2 product = new Product2("Jacket", "Jacket_Description", new BigDecimal("100"));
        product.setGroupId(group);
        group.getProducts().add(product);
        group2Dao.save(group);
        Assert.assertEquals(1, group2Dao.findByName("Food").size());
        group2Dao.deleteById(group.getId());
    }
}

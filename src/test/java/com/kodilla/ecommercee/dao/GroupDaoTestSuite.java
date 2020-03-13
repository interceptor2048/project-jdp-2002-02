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
public class GroupDaoTestSuite {

    @Autowired
    Group2Dao group2Dao;
    @Autowired
    Product2Dao product2Dao;

    @Test
    public void testGroup2DaoSave() {
        Group group = new Group();
                group.setName("Food");
        Product product = new Product();
                    product.setName("Jacket");
                    product.setDescription("Jacket_Description");
                    product.setPrice(new BigDecimal("100"));
                    product.setGroupId(group);
        group.getProducts().add(product);
        group2Dao.save(group);
        Assert.assertEquals(1, group2Dao.findByName("Food").size());
        group2Dao.deleteById(group.getId());
    }
}

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
public class GroupDaoTestSuit {

    @Autowired
    GroupDao groupDao;
    @Autowired
    ProductDao productDao;

    @Test
    public void testGroupDaoSave() {
        Group group = new Group();
                group.setName("Food");
        Product product = new Product();
                    product.setName("Jacket");
                    product.setDescription("Jacket_Description");
                    product.setPrice(new BigDecimal("100"));
                    product.setGroup(group);
        group.getProducts().add(product);
        groupDao.save(group);
        Assert.assertEquals(1, groupDao.findByName("Food").size());
        groupDao.deleteById(group.getId());
    }
}

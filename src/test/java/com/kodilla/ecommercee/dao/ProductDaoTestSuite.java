package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductDaoTestSuite {
    @Autowired
    ProductDao productDao;

    @Autowired
    GroupDao groupDao;

    @Test
    public void testProductDaoSave() {
        Group group = new Group();
        group.setName("group1");
        groupDao.save(group);
        Long groupId = group.getId();
        Product product = new Product();
        product.setName("Milk");
        product.setPrice(new BigDecimal("22.5"));
        product.setDescription("FoodType");
        product.setGroupId(group);
        productDao.save(product);
        List<Product> productsList = new ArrayList<>();
        productDao.findAll().forEach(productsList::add);
        Assert.assertEquals(1, productDao.findByName("Milk").size());
        Assert.assertEquals(true, productsList.stream().anyMatch(product1 -> product1.getGroupId().getId().equals(groupId)));
        productDao.deleteAll();
    }
}



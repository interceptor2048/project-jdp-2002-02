package com.kodilla.ecommercee.Utils;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class ProductEntityObjectCreator {

    private static final String DEFAULT_PRODUCT_NAME = "productNameDefault";
    private static final String UPDATED_PRODUCT_NAME = "productUpdated";

    private static final BigDecimal DEFAULT_PRICE = BigDecimal.TEN;
    private static final BigDecimal UPDATED_PRICE = BigDecimal.ZERO;

    private static final String DEFAULT_DESCRIPTION = "DESCRIPTION";
    private static final String UPDATED_DESCRIPTION = "UPDATED_DESCRIPTION";

    public static Product createEntity() {
        Product product = Product.builder()
                .name(DEFAULT_PRODUCT_NAME)
                .description(DEFAULT_DESCRIPTION)
                .price(DEFAULT_PRICE)
                .available(true)
                .build();

        return product;
    }

    public static Product createUpdatedEntityWithGivenId(Long id) {
        Product product = Product.builder()
                .id(id)
                .name(UPDATED_PRODUCT_NAME)
                .description(UPDATED_DESCRIPTION)
                .price(UPDATED_PRICE)
                .available(true)
                .build();
        return product;
    }
}

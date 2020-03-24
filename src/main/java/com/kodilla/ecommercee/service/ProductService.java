package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public List<Product> getProducts();

    public Optional<Product> getProduct(final Long id);

    public Product saveProduct(final Product product);

    public void deleteProduct(Long id);

    public boolean exists(Long productId);
}

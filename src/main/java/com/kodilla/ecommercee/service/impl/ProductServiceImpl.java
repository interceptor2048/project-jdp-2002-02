package com.kodilla.ecommercee.service.impl;

import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productRepository;

    public ProductServiceImpl(ProductDao productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(final Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(new Product());
    }

    public boolean exists(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Null argument for Id was passed");
        }
        if(productRepository.findById(id).isPresent()) {
            return true;
        }
        return false;
    }
}

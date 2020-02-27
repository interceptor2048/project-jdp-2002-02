package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee/products")
public class ProductController {

    @GetMapping(path = "/products")
    public List<ProductDto> getAllProducts() {
        return new ArrayList<ProductDto>();
    }

    @GetMapping(path = "/products")
    public ProductDto getProduct(@RequestParam Long productId) {
        return new ProductDto();
    }

    @PostMapping(path = "/products", consumes = "application/json")
    public void createProduct(@RequestBody ProductDto productDto) {
    }

    @PutMapping(path = "/products")
    public void updateProduct(@RequestBody ProductDto productDto) {
    }

    @DeleteMapping(path = "/products")
    public void deleteProduct(@RequestParam Long productId) {
    }
}

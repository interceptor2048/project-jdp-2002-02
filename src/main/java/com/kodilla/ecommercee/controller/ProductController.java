package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee/products")
public class ProductController {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getAllProducts() {
        return new ArrayList<ProductDto>();
    }

    @GetMapping(path = "/products")
    public ProductDto getProduct(@RequestParam Long productId) {
        return new ProductDto();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct(@RequestBody ProductDto productDto) {
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long productId) {
    }
}

package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getAllProducts() {
        return productMapper.mapToProductDtoList(productService.getProducts());
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto getProduct(@PathVariable("id") Long id) throws NotFoundException {
        return productMapper.mapToProductDto(productService.getProduct(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody(required = false) ProductDto productDto) {
        return productMapper.mapToProductDto(productService.saveProduct(productMapper.mapToProduct(productDto)));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}

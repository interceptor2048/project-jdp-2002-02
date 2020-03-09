package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;

import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ecommercee")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.GET, value = "products")
    public List<ProductDto> getAllProducts() {
        return productMapper.mapToProductDtoList(productService.getProducts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "product")
    public ProductDto getProduct(@RequestParam Long id) throws NotFoundException {
        return productMapper.mapToProductDto(productService.getProduct(id).orElseThrow(NotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = "application/json")
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productMapper.mapToProductDto(productService.saveProduct(productMapper.mapToProduct(productDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }
}

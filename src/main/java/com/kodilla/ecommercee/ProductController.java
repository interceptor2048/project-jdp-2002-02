package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/ecommercee")
@Transactional
public class ProductController {

    @GetMapping(value = "products", consumes = "application/json")
    public List<ProductDto> getAllProducts() {
        return new ArrayList<ProductDto>();
    }

    @PostMapping(value = "products", consumes = "application/json")
    public ProductDto getProduct(@RequestParam Long productId) {
        return new ProductDto() ;
    }

    @PutMapping(value = "products", consumes = "application/json")
    public void createProduct(@RequestBody ProductDto productDto) {
    }

    @PutMapping(value = "products", consumes = "application/json")
    public void updateProduct(@RequestBody ProductDto productDto) {
    }

    @DeleteMapping(value = "products", consumes = "application/json")
    public void deleteProduct(@RequestParam ProductDto productDto) {
    }
}

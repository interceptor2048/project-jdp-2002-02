package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dao.GroupDao;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    GroupDao groupRepository;

    public Product mapToProduct(final ProductDto productDto) {
        Product productBean = new Product();
                productBean.setId(productDto.getId());
                productBean.setName(productDto.getName());
                productBean.setPrice(productDto.getPrice());
                productBean.setDescription(productDto.getDescription());
                productBean.setGroupId(groupRepository.findById(productDto.getGroupId()).orElse(null));

        return productBean;
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getGroupId().getId());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {
        return products.stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getDescription(), p.getGroupId().getId()))
                .collect(Collectors.toList());
    }
}

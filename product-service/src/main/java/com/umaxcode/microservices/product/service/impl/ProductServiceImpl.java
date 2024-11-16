package com.umaxcode.microservices.product.service.impl;

import com.umaxcode.microservices.product.domain.dto.ProductCreationRequestDTO;
import com.umaxcode.microservices.product.domain.dto.ProductResponseDTO;
import com.umaxcode.microservices.product.domain.entity.Product;
import com.umaxcode.microservices.product.repository.ProductRepository;
import com.umaxcode.microservices.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(ProductCreationRequestDTO productRequest) {

        Product productInstance = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        Product savedProduct = productRepository.save(productInstance);
        log.info("Product {} added successfully", productInstance.getName());

        return ProductResponseDTO.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .build();
    }

    @Override
    public List<ProductResponseDTO> getAllProduct() {
        return productRepository.findAll()
                .stream().map(product -> ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build()
                ).toList();
    }
}

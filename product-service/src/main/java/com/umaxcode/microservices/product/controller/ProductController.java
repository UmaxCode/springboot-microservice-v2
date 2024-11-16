package com.umaxcode.microservices.product.controller;

import com.umaxcode.microservices.product.domain.dto.ProductCreationRequestDTO;
import com.umaxcode.microservices.product.domain.dto.ProductResponseDTO;
import com.umaxcode.microservices.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@RequestBody ProductCreationRequestDTO productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getAllProducts() {

        return productService.getAllProduct();
    }
}

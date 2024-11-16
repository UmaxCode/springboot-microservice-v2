package com.umaxcode.microservices.product.service;

import com.umaxcode.microservices.product.domain.dto.ProductCreationRequestDTO;
import com.umaxcode.microservices.product.domain.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductCreationRequestDTO productRequest);

    List<ProductResponseDTO> getAllProduct();
}

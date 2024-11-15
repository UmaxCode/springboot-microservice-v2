package com.umaxcode.microservices.product.service;

import com.umaxcode.microservices.product.domain.dto.ProductCreationRequestDTO;
import com.umaxcode.microservices.product.domain.dto.ProductResponseDTO;

public interface ProductService {

    ProductResponseDTO createProduct(ProductCreationRequestDTO productRequest);
}

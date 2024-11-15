package com.umaxcode.microservices.product.repository;

import com.umaxcode.microservices.product.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}

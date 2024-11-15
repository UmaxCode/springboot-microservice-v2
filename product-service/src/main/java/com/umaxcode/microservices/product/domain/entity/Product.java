package com.umaxcode.microservices.product.domain.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "products")
public class Product {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;
}

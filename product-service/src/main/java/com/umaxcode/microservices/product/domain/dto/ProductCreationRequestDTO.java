package com.umaxcode.microservices.product.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductCreationRequestDTO(
        String name,
        String description,
        BigDecimal price
) {
}

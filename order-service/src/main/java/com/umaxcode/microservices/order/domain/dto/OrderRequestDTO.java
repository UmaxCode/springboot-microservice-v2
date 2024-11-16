package com.umaxcode.microservices.order.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderRequestDTO(
        String skuCode,
        BigDecimal price, Integer quantity
) {
}

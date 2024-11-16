package com.umaxcode.microservices.order.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderResponseDTO(
        Long id,
        String orderNumber, String skuCode,
        BigDecimal price, Integer quantity
) {
}

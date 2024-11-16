package com.umaxcode.microservice.inventory.domain.dto;

import lombok.Builder;

@Builder
public record InventoryResponseDTO(
        Long id,
        String skuCode,
        int quantity
) {
}

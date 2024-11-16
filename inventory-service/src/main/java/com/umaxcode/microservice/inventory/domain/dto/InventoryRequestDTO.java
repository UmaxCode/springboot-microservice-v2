package com.umaxcode.microservice.inventory.domain.dto;

public record InventoryRequestDTO(
        String skuCode,
        int quantity
) {
}

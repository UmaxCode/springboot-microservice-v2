package com.umaxcode.microservice.inventory.service;

import com.umaxcode.microservice.inventory.domain.dto.InventoryRequestDTO;
import com.umaxcode.microservice.inventory.domain.dto.InventoryResponseDTO;
import com.umaxcode.microservice.inventory.domain.entity.Inventory;
import com.umaxcode.microservice.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    public InventoryResponseDTO addInventory(InventoryRequestDTO request) {

        Inventory inventory = Inventory.builder()
                .skuCode(request.skuCode())
                .quantity(request.quantity())
                .build();

        Inventory savedInventory = inventoryRepository.save(inventory);

        return InventoryResponseDTO.builder()
                .id(savedInventory.getId())
                .skuCode(savedInventory.getSkuCode())
                .quantity(savedInventory.getQuantity())
                .build();

    }

    public boolean isInSock(InventoryRequestDTO request) {

        return inventoryRepository.existsInventoryBySkuCodeAndQuantityIsGreaterThanEqual(request.skuCode(), request.quantity());
    }


    private void validateInventoryRequest(InventoryRequestDTO request) {

        if (!inventoryRepository.existsInventoryBySkuCodeAndQuantityIsGreaterThanEqual(request.skuCode(), request.quantity())) {
            throw new IllegalArgumentException("Invalid request");
        }
    }

    public List<InventoryResponseDTO> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(inventory -> InventoryResponseDTO.builder()
                        .id(inventory.getId())
                        .skuCode(inventory.getSkuCode())
                        .quantity(inventory.getQuantity())
                        .build())
                .toList();
    }
}

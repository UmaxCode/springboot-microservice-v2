package com.umaxcode.microservice.inventory.controller;

import com.umaxcode.microservice.inventory.domain.dto.InventoryRequestDTO;
import com.umaxcode.microservice.inventory.domain.dto.InventoryResponseDTO;
import com.umaxcode.microservice.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponseDTO addInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO) {
        return inventoryService.addInventory(inventoryRequestDTO);
    }

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInSock(@RequestParam String skuCode, @RequestParam int quantity) {
        return inventoryService.isInSock(skuCode, quantity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }
}

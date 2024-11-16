package com.umaxcode.microservice.inventory.repository;

import com.umaxcode.microservice.inventory.domain.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsInventoryBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, int quantity);
}

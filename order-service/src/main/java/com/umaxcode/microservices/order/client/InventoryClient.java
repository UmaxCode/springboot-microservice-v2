package com.umaxcode.microservices.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory-service", url = "${application.inventory.url}", path = "/api/v1/inventories")
public interface InventoryClient {

    @GetMapping(value = "/status")
    boolean isInStock(@RequestParam(value = "skuCode") String skuCode,
                      @RequestParam(value = "quantity") int quantity);

}

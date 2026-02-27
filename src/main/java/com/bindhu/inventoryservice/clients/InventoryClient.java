package com.bindhu.inventoryservice.clients;

import com.bindhu.inventoryservice.dtos.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {

    @PostMapping("/inventory/reduce")
    boolean reduceStock(@RequestBody OrderRequest request);
}
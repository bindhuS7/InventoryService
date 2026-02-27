package com.bindhu.inventoryservice.controller;

import com.bindhu.inventoryservice.dtos.InventoryRequest;
import com.bindhu.inventoryservice.dtos.InventoryResponse;
import com.bindhu.inventoryservice.dtos.OrderRequest;
import com.bindhu.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/add")
    public InventoryResponse addStock(@RequestBody InventoryRequest request) {

        log.info(" add stock request" + request);
        return   inventoryService.addStock(request);
    }

    @PostMapping("/addBulk")
    public List<InventoryResponse> addBulkStock(@RequestBody List<InventoryRequest> request) {

        log.info(" add stock request" + request);
        return   inventoryService.addBulkStock(request);
    }
    @GetMapping("/getAllInventoriesStockGT0")
    public List<InventoryResponse>getAllInventoriesStockGT0(){
        log.info("entered get all inventory stocks greater than 0");
        return inventoryService.getAllInventoriesStockGT0();
    }

    @PostMapping("/reduce")
    public boolean reduceStock(@RequestBody OrderRequest request) {

        log.info("request" + request);
      return   inventoryService.reduceStock(request);
    }



}

package com.bindhu.inventoryservice.service;

import com.bindhu.inventoryservice.dtos.InventoryRequest;
import com.bindhu.inventoryservice.dtos.InventoryResponse;
import com.bindhu.inventoryservice.dtos.OrderRequest;
import com.bindhu.inventoryservice.entity.Inventory;
import com.bindhu.inventoryservice.respository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean reduceStock(OrderRequest request) {

        Inventory inventory=inventoryRepository.findByProductCode(request.getProductCode());
        if(!(inventory.getStock()>request.getQuantity())){
            System.out.println("out of stock");
            return false;
        }
        inventory.setStock(inventory.getStock()-request.getQuantity());
        inventoryRepository.save(inventory);
        System.out.println("Stock Reduced Successfully");
        return true;


    }

    public InventoryResponse addStock(InventoryRequest request) {
     log.info("add stock () of inventory service");

       Inventory inventory= mapToInventory(request);
        inventoryRepository.save(inventory);

        return mapToResponse(inventory);
    }

    public List<InventoryResponse> addBulkStock(List<InventoryRequest> request) {
        log.info("adding bulk inventory");
        List<Inventory> list=request.stream().map(this::mapToInventory).toList();

        inventoryRepository.saveAll(list);
return getAllInventoriesStockGT0();
    }
    public Inventory mapToInventory(InventoryRequest request){

        log.info("convert request  into inventory");
        Inventory oldInventory=inventoryRepository.findByProductCode(request.getProductCode());
        if(oldInventory!=null){
            oldInventory.setStock(oldInventory.getStock()+request.getStock());
            return oldInventory;
        }
                Inventory inventory=new Inventory();
                BeanUtils.copyProperties(request,inventory);
                return inventory;

    }

    public List<InventoryResponse> getAllInventoriesStockGT0() {
        log.info("entered get All Inventories Stock greater than 0");
        List<Inventory>list=inventoryRepository.findStockGreaterThanZero();
        return list.stream().map(this::mapToResponse).toList();
    }

    private InventoryResponse mapToResponse(Inventory inventory) {
        log.info("entered into get all map to response");
        InventoryResponse response=new InventoryResponse();
        BeanUtils.copyProperties(inventory,response);
//        response.set
        return response;

    }
}

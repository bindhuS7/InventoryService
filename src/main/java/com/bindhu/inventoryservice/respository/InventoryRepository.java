package com.bindhu.inventoryservice.respository;


import com.bindhu.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Inventory findByProductCode(String productCode);
    @Query(value = "SELECT * FROM inventory WHERE stock > 0", nativeQuery = true)
    List<Inventory> findStockGreaterThanZero();

}

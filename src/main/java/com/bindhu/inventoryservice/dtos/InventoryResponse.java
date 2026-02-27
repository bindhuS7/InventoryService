package com.bindhu.inventoryservice.dtos;

import lombok.Data;

@Data
public class InventoryResponse {
    private Long id;
    private String productCode;
    private int stock;
    private double price;
}

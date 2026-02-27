package com.bindhu.inventoryservice.clients;

import com.bindhu.inventoryservice.dtos.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {

    @PostMapping("/payment/pay")
    boolean pay(@RequestBody OrderRequest request);

    @PostMapping("/payment/refund")
    void refund(@RequestBody OrderRequest request);
}
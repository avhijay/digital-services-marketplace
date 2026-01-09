package com.marketplace.order_service.client;



import com.marketplace.order_service.dto.internal.InternalProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CATALOG-SERVICE",contextId = "catalogClient")
public interface CatalogClient {

    @GetMapping("/products/get/{productId}")
    InternalProductDto getProductById(@PathVariable Long productId);
}

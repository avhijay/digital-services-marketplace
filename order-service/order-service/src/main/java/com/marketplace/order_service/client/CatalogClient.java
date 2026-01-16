package com.marketplace.order_service.client;



import com.marketplace.order_service.dto.product.InternalProductDto;
import com.marketplace.order_service.dto.product.ProductQuantity;
import com.marketplace.order_service.dto.product.ValidateProductsRequest;
import com.marketplace.order_service.dto.product.ValidateProductsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "CATALOG-SERVICE",contextId = "catalogClient")
public interface CatalogClient {

    @GetMapping("/products/get/{productId}")
    InternalProductDto getProductById(@PathVariable Long productId);

    @PostMapping("/products/internal/validate-for-order")
    ValidateProductsResponse validateProduct(@RequestBody ValidateProductsRequest validateProductsRequest);


    @PostMapping("/products/internal/decrease-stock")
    Void updateStockForOrder(@RequestBody List<ProductQuantity> items);


}

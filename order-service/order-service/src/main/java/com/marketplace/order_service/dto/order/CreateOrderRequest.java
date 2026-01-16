package com.marketplace.order_service.dto.order;

import com.marketplace.order_service.dto.product.ProductQuantity;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        @NotNull
        Long userId,
        @NotNull
        List<ProductQuantity>productQuantities
) {
}

package com.marketplace.order_service.dto.internal;

public record StockUpdateInternal(
        Long productId,
        Integer quantity
) {
}
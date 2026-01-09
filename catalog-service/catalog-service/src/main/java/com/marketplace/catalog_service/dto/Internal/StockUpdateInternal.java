package com.marketplace.catalog_service.dto.Internal;

public record StockUpdateInternal(
        Long productId,
        Integer quantity
) {
}

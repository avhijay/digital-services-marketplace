package com.marketplace.catalog_service.dto.Internal;

import com.marketplace.catalog_service.enums.Status;

import java.math.BigDecimal;

public record ValidateProductResponse(

        Long productId,
        BigDecimal unitPrice,
        String currency,
        Integer availableStock,
        Status status



) {
}

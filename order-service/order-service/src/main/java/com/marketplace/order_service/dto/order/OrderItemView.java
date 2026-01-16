package com.marketplace.order_service.dto.order;

import java.math.BigDecimal;

public record OrderItemView(
        Long productId,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal lineTotal

) {
}

package com.marketplace.order_service.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        Long orderId,
        Long userId,
        BigDecimal totalAmount,
        String status,
        Instant createdAt,
        List<OrderItemResponse> items
) {}


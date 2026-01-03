package com.marketplace.order_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        Long orderId,
        Long userId,
        @Positive
        @NotNull
        BigDecimal totalAmount,
        String status,
        Instant createdAt,
        List<OrderItemResponse> items
) {}


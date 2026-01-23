package com.marketplace.payment_service.dto;

import com.marketplace.payment_service.entity.Status;

import java.math.BigDecimal;

public record PaymentRequest(
        String orderReference,
        Long orderId,
        BigDecimal amount,
        Status status,
        String method,
        String provider,
        String providerPaymentId

) {
}

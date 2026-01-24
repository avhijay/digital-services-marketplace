package com.marketplace.payment_service.dto;

import com.marketplace.payment_service.enums.Method;
import com.marketplace.payment_service.enums.Status;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentResponse (
        Long id,
        UUID paymentId,
        String orderReference,
        Long orderId,
        BigDecimal amount,
        Status status,
        Method method,
        String provider,
        String providerPaymentId




){
}

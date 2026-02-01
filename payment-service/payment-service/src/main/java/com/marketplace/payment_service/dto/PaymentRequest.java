package com.marketplace.payment_service.dto;

import com.marketplace.payment_service.enums.Method;
import com.marketplace.payment_service.enums.Status;
import com.marketplace.payment_service.enums.Currency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequest(
        @NotNull
        String orderReference,
        @NotNull
        Long orderId,
        @NotNull
        @Positive
        BigDecimal amount,
        @NotNull
        Currency currency,
        @NotNull
        String status,
        @NotNull
        Method method,
        @NotNull
        String provider,
        @NotNull
        String providerPaymentId

) {
}

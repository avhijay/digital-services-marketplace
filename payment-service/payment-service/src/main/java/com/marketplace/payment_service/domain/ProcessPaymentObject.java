package com.marketplace.payment_service.domain;

import com.marketplace.payment_service.enums.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public class ProcessPaymentObject {

    private final UUID paymentId;
    private final BigDecimal amount;
    private final Currency currency;
    private final String providerPaymentId;

    public ProcessPaymentObject(UUID paymentId, String providerPaymentId, Currency currency, BigDecimal amount) {
        this.paymentId = paymentId;
        this.providerPaymentId = providerPaymentId;
        this.currency = currency;
        this.amount = amount;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public String getProviderPaymentId() {
        return providerPaymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}

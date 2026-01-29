package com.marketplace.payment_service.domain;

import com.marketplace.payment_service.enums.FailureReasons;
import com.marketplace.payment_service.enums.ProviderStatus;

import java.util.UUID;

public class ProviderResultObject {

    private final FailureReasons failureReasons;
    private final String providerPaymentId;
    private final UUID paymentId;
    private final ProviderStatus providerStatus;


    public ProviderResultObject(FailureReasons failureReasons, String providerPaymentId, UUID paymentId, ProviderStatus providerStatus) {
        this.failureReasons = failureReasons;
        this.providerPaymentId = providerPaymentId;
        this.paymentId = paymentId;
        this.providerStatus = providerStatus;
    }


    public FailureReasons getFailureReasons() {
        return failureReasons;
    }

    public String getProviderPaymentId() {
        return providerPaymentId;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public ProviderStatus getProviderStatus() {
        return providerStatus;
    }
}

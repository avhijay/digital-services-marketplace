package com.marketplace.payment_service.provider;

import com.marketplace.payment_service.domain.ProcessPaymentObject;
import com.marketplace.payment_service.domain.ProviderResultObject;
import com.marketplace.payment_service.enums.FailureReasons;

public interface PaymentProvider {

    ProviderResultObject processPaymentPass(ProcessPaymentObject object);
    ProviderResultObject processPaymentFail(ProcessPaymentObject object);



}

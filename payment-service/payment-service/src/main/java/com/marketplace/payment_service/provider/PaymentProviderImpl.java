package com.marketplace.payment_service.provider;

import com.marketplace.payment_service.domain.ProcessPaymentObject;
import com.marketplace.payment_service.domain.ProviderResultObject;
import com.marketplace.payment_service.enums.FailureReasons;
import com.marketplace.payment_service.enums.ProviderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentProviderImpl implements PaymentProvider{
    private static final Logger log = LoggerFactory.getLogger(PaymentProviderImpl.class);

    @Override
    public ProviderResultObject processPaymentPass(ProcessPaymentObject object) {
        log.info("Processing payment :{}",object.getPaymentId());
        return new ProviderResultObject(null,object.getProviderPaymentId(),object.getPaymentId(), ProviderStatus.SUCCESS);
    }

    @Override
    public ProviderResultObject processPaymentFail(ProcessPaymentObject object) {
        return new ProviderResultObject(FailureReasons.PROVIDER_500,object.getProviderPaymentId(),object.getPaymentId(),ProviderStatus.FAILURE);
    }
}


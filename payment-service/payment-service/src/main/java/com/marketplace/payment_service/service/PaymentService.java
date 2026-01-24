package com.marketplace.payment_service.service;

import com.marketplace.payment_service.dto.PaymentRequest;
import com.marketplace.payment_service.dto.PaymentResponse;
import com.marketplace.payment_service.enums.Status;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest paymentRequest);
    PaymentResponse getPaymentById(Long id);
    PaymentResponse getPaymentByPaymentId(UUID paymentId);

    List<PaymentResponse>getPaymentByStatus(Status status);
    List<PaymentResponse>getPaymentByProvider(String ProviderPaymentId);


}

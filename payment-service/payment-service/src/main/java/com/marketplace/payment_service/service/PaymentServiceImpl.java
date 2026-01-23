package com.marketplace.payment_service.service;

import com.marketplace.payment_service.dto.PaymentRequest;
import com.marketplace.payment_service.dto.PaymentResponse;
import com.marketplace.payment_service.entity.Status;

import java.util.List;
import java.util.UUID;

public class PaymentServiceImpl implements  PaymentService{
    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        return null;
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        return null;
    }

    @Override
    public PaymentResponse getPaymentByPaymentId(UUID paymentId) {
        return null;
    }

    @Override
    public List<PaymentResponse> getPaymentByStatus(Status status) {
        return List.of();
    }

    @Override
    public List<PaymentResponse> getPaymentByProvider(String ProviderPaymentId) {
        return List.of();
    }
}

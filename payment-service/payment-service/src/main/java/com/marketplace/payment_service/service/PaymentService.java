package com.marketplace.payment_service.service;

import com.marketplace.payment_service.dto.PaymentRequest;
import com.marketplace.payment_service.dto.PaymentResponse;
import com.marketplace.payment_service.entity.Payment;
import com.marketplace.payment_service.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest paymentRequest ,String idempotencyKey);
    void processPayment(UUID paymentId);
     PaymentResponse retryPayment(UUID paymentId);

    PaymentResponse getPaymentById(Long id);
    PaymentResponse getPaymentByPaymentId(UUID paymentId);

    Page<PaymentResponse> getPaymentByStatus(Status status  , Pageable pageable);
    Page<PaymentResponse>getPaymentByProvider(String ProviderPaymentId , Pageable pageable);

PaymentResponse getPaymentByIdempotencyKey(String idempotencyKey);

}

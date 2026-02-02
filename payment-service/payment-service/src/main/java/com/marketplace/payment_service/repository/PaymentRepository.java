package com.marketplace.payment_service.repository;

import com.marketplace.payment_service.entity.Payment;
import com.marketplace.payment_service.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Payment findByPaymentId(UUID paymentId);
    Payment findByOrderId(Long OrderId);
    Payment findByOrderReference(String orderReference);
    Page<Payment> findAllByStatus(Status status , Pageable pageable);
    Payment findByIdempotencyKey(String idempotencyKey);
    Page<Payment>findAllByStatusAndRetryCountLess(Integer count, Pageable pageable);
    Page<Payment>findByProviderPaymentId(String providerPaymentId , Pageable pageable);
}

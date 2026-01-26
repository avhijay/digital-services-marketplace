package com.marketplace.payment_service.repository;

import com.marketplace.payment_service.entity.Payment;
import com.marketplace.payment_service.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Payment findByPaymentId(UUID paymentId);
    Payment findByOrderId(Long OrderId);
    Payment findByOrderReference(String orderReference);
    List<Payment>findAllByStatus(Status status);
    List<Payment>findAllByStatusAndRetryCountLess(Integer count);
    List<Payment>findByProviderPaymentId(String providerPaymentId);
}

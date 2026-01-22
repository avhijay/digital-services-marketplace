package com.marketplace.payment_service.entity;


import com.marketplace.payment_service.enums.Currency;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

import java.util.UUID;
@Getter
@Setter
@Entity
@NoArgsConstructor

@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "order_reference" , nullable = false , unique = true)
    private  String orderReference;



    @Column(name = "order_id",nullable = false)
    private Long orderId;


    @Column(name = "amount",nullable = false , precision = 12 ,scale = 2)
    private BigDecimal amount;

    @Column(name = "currency" ,nullable = false ,length = 5)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "status",nullable = false ,length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "method",nullable = false)
    private Status method;

    @Column(name = "provider",nullable = false ,length = 50)
    private String provider;

    @Column(name = "provider_payment_id",nullable = false,length = 100)
    private String providerPaymentId;

    @Column(name = "failure_reason")
    private String failureReason;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "created_at",nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at",nullable = false)
    private Instant updatedAt;


    public Payment(UUID paymentId, String orderReference, Long orderId, BigDecimal amount, Currency currency, Status status, Status method, String provider, String providerPaymentId, String failureReason, Integer retryCount) {
        this.paymentId = paymentId;
        this.orderReference = orderReference;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.method = method;
        this.provider = provider;
        this.providerPaymentId = providerPaymentId;
        this.failureReason = failureReason;
        this.retryCount = retryCount;
    }
}

package com.marketplace.payment_service.entity;


import com.marketplace.payment_service.enums.Currency;
import com.marketplace.payment_service.enums.FailureReasons;
import com.marketplace.payment_service.enums.Method;
import com.marketplace.payment_service.enums.Status;
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

    @Column(name = "order_reference" , nullable = false )
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
    @Enumerated(EnumType.STRING)
    private     Method method;

    @Column(name = "provider",nullable = false ,length = 50)
    private String provider;

    @Column(name = "provider_payment_id",nullable = false,length = 100)
    private String providerPaymentId;

    @Column(name = "failure_reason")
    @Enumerated(EnumType.STRING)
    private FailureReasons failureReason;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "idempotency_key",nullable = false,unique = true)
    private String idempotencyKey;


    @Column(name = "next_retry_at",nullable = true)
    private Instant nextRetry;

    @Column(name = "created_at",nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at",nullable = false)
    private Instant updatedAt;




    @Version

    private Long version;





    @PrePersist
    void onCreate(){
        createdAt=Instant.now();

    }

    @PreUpdate
    void onUpdate(){
        updatedAt=Instant.now();
    }


    public Payment(Instant updatedAt, UUID paymentId, String orderReference, Long orderId, Currency currency, BigDecimal amount, Method method, Status status, String provider, FailureReasons failureReason,
                   String providerPaymentId, Integer retryCount, String idempotencyKey, Instant nextRetry, Instant createdAt) {
        this.updatedAt = updatedAt;
        this.paymentId = paymentId;
        this.orderReference = orderReference;
        this.orderId = orderId;
        this.currency = currency;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.provider = provider;
        this.failureReason = failureReason;
        this.providerPaymentId = providerPaymentId;
        this.retryCount = retryCount;
        this.idempotencyKey = idempotencyKey;
        this.nextRetry = nextRetry;
        this.createdAt = createdAt;
    }
}

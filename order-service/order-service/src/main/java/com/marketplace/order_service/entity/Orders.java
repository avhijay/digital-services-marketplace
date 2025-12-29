package com.marketplace.order_service.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name ="order_number",nullable = false,unique = true , length = 50)
    private String orderNumber;

    @Column(name = "user_id",nullable = false)
    private Long userId;


    @Column(name = "status",nullable = false , length = 20)
    private String status;

    @Column(name = "total_amount", precision = 12 ,scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "currency",nullable = false , length = 5)
    private String currency;

    @Column(name = "created_at",nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


    @PrePersist
    void onCreate(){
        createdAt= Instant.now();
        updatedAt=createdAt;
    }


    @PreUpdate
    void onUpdate(){
        updatedAt =Instant.now();
    }


    public Orders(){}


    public Orders(String orderNumber, Long userId, String status, BigDecimal totalAmount, String currency, Instant createdAt, Instant updatedAt) {
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

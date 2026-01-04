package com.marketplace.catalog_service.entity;


import com.marketplace.catalog_service.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
@Getter
@Setter
@ToString
@Entity
@Table(name = "catalog",uniqueConstraints = @UniqueConstraint(name = "uk_catalog_name",columnNames = "name"))
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;


    @Column(name = "name" , nullable = false , length = 255)
    private String name;


    @Column(name = "description",nullable = true)
    private String description;

    @Column(name = "price",nullable = false,precision = 12,scale = 2)
    private BigDecimal price;

    @Column(name = "currency",nullable = false,length = 5)
    private String currency;

    @Column(name = "stock",nullable = false)
    private Integer stock;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false , length = 20)
    private Status status;

    @Column(name = "created_at", nullable = false)
    @Setter(AccessLevel.NONE)
    private Instant createdAt;


    @Column(name = "updated_at")
    @Setter(AccessLevel.NONE)
    private Instant updatedAt;



    @Version
    private Long version;

    @PrePersist
    void onCreate(){
        createdAt=Instant.now();
        updatedAt=createdAt;

    }

    @PreUpdate
    void onUpdate(){
        updatedAt=Instant.now();
    }


public Catalog(){}


    public Catalog(String name, String description, BigDecimal price, String currency, Integer stock, Status status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.status = status;
    }
}

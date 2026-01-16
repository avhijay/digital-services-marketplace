package com.marketplace.order_service.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
@Getter
@Setter
@Entity
@Table(name = "order_items", uniqueConstraints ={
        @UniqueConstraint(name = "uk_order_product",columnNames ={"order_id","product_id"}) // making order id and product id unique as a whole together
})
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
            @Setter(AccessLevel.NONE)
    Long id;

    @Column(name = "order_id",nullable = false)
    Long orderId;


    @Column(name = "product_id" ,nullable = false )
    Long productId;


    @Column(name = "quantity",nullable = false)
    Integer quantity;

    @Column(name = "unit_price", nullable = false , precision = 12, scale  = 2)
    BigDecimal unitPrice;

    @Column(name = "line_total",nullable = false,precision = 12,scale = 2)
    BigDecimal lineTotal;


    public OrderItems(){}

    public OrderItems(Long orderId, Long productId, Integer quantity, BigDecimal unitPrice, BigDecimal lineTotal) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }
}

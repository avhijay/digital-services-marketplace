package com.marketplace.order_service.dto;

import java.math.BigDecimal;

public record OrderItemResponse (

        Long productItemId,
        Long productId,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal lineTotal


){}

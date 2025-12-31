package com.marketplace.order_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderItemRequest (

    @NotNull
    Long productId,

    @NotNull
    @Min(1)
    Integer quantity,

    @NotNull
    @Positive
    BigDecimal unitPrice

)
{}

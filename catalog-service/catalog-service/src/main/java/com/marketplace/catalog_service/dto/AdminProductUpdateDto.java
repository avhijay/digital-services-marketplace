package com.marketplace.catalog_service.dto;

import com.marketplace.catalog_service.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record AdminProductUpdateDto(



    String name,
    String description,


    BigDecimal price,

    String currency,

    Integer stock,
    Status status
)
    {}

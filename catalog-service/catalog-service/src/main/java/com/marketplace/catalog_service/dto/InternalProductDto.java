package com.marketplace.catalog_service.dto;

import com.marketplace.catalog_service.enums.Status;

import java.math.BigDecimal;

public record InternalProductDto(

        Long id,
        String name,
        BigDecimal price,
        String currency,
        Integer stock,
        Status status

        )









{
}

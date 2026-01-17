package com.marketplace.catalog_service.dto;


import com.marketplace.catalog_service.enums.Currency;
import com.marketplace.catalog_service.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;


public record AdminProductDto (

@NotNull
 String name,
 String description,
 @NotNull
@PositiveOrZero
 BigDecimal price,
 @NotNull
Currency currency,
 @Positive
 Integer stock,
 Status status
)

{}

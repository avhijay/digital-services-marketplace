package com.marketplace.catalog_service.dto;

import com.marketplace.catalog_service.enums.Currency;
import com.marketplace.catalog_service.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public record PublicProductResponse  (

Long id,
String name,
String description,
@PositiveOrZero
BigDecimal price,
Currency currency,
@PositiveOrZero
Integer stock,
Status status



)

{}





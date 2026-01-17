package com.marketplace.catalog_service.dto.Internal;

import com.marketplace.catalog_service.enums.Currency;
import com.marketplace.catalog_service.enums.Status;

import java.math.BigDecimal;

public record InternalProductDto(

        Long id,
        String name,
        BigDecimal price,
                Currency currency,
        Integer stock,
        Status status

        )









{
}

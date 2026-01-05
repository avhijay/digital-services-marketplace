package com.marketplace.order_service.dto;

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
    public enum Status {
        ACTIVE,
        INACTIVE,
        OUT_OF_STOCK,
        DISCONTINUED
    }

}
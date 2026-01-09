package com.marketplace.order_service.dto.internal;

import java.math.BigDecimal;
import java.util.List;

public record InternalProductDto(

        Long id,
        String name,
        BigDecimal price,
        String currency,
        Integer stock,
        Status status,
        List<Long>productIdAndStock

)









{
    public enum Status {
        ACTIVE,
        INACTIVE,
        OUT_OF_STOCK,
        DISCONTINUED
    }

}
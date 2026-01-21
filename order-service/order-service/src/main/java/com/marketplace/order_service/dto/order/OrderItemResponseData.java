package com.marketplace.order_service.dto.order;



import com.marketplace.order_service.enums.Currency;

import java.math.BigDecimal;

public record OrderItemResponseData(

        Long productId,
        BigDecimal unitPrice,
        Currency currency,
        Integer quantity,
       BigDecimal lineTotal

) {

    private enum Status {
        ACTIVE,
        INACTIVE,
        OUT_OF_STOCK,
        DISCONTINUED
    }
}



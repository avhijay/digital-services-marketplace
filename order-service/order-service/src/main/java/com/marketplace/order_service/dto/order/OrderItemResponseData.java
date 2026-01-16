package com.marketplace.order_service.dto.order;



import java.math.BigDecimal;

public record OrderItemResponseData(

        Long productId,
        BigDecimal unitPrice,
        String currency,
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



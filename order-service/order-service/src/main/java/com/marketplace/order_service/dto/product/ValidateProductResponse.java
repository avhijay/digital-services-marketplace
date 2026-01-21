package com.marketplace.order_service.dto.product;





import com.marketplace.order_service.enums.Currency;

import java.math.BigDecimal;

public record ValidateProductResponse(

        Long productId,
        BigDecimal unitPrice,
        Currency currency,
        Integer availableStock,
        Status status



) {

    private enum Status {
        ACTIVE,
        INACTIVE,
        OUT_OF_STOCK,
        DISCONTINUED
    }


}

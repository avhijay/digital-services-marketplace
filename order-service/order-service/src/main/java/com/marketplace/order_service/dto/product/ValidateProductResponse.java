package com.marketplace.order_service.dto.product;





import java.math.BigDecimal;

public record ValidateProductResponse(

        Long productId,
        BigDecimal unitPrice,
        String currency,
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

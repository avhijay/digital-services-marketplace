package com.marketplace.order_service.dto.internal;

import java.util.List;

public record ValidateProductsRequest(
        List<ProductQuantity> productQuantities

) {
}


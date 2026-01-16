package com.marketplace.order_service.dto.product;

import java.util.List;

public record ValidateProductsRequest(
        List<ProductQuantity> productQuantities

) {
}


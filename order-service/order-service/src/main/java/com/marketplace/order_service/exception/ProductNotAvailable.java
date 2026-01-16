package com.marketplace.order_service.exception;

public class ProductNotAvailable extends RuntimeException {
    public ProductNotAvailable(String message) {
        super(message);
    }
}

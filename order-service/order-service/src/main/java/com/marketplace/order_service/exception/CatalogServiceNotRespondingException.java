package com.marketplace.order_service.exception;

public class CatalogServiceNotRespondingException extends RuntimeException {
    public CatalogServiceNotRespondingException(String message) {
        super(message);
    }
}

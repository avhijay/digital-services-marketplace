package com.marketplace.payment_service.exception;

public class UniqueConstraintVoilationException extends RuntimeException {
    public UniqueConstraintVoilationException(String message) {
        super(message);
    }
}

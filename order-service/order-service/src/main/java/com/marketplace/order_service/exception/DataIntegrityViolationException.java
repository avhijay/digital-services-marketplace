package com.marketplace.order_service.exception;

public class DataIntegrityViolationException extends RuntimeException {
    public DataIntegrityViolationException(String message){
        super(message);

    }
}

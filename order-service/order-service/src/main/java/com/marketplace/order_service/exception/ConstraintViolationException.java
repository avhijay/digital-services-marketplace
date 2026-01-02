package com.marketplace.order_service.exception;

public class ConstraintViolationException extends RuntimeException {
    public ConstraintViolationException(String message){
        super(message);
    }
}

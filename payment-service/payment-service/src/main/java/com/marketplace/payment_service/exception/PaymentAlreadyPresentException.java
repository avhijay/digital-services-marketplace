package com.marketplace.payment_service.exception;

public class PaymentAlreadyPresentException extends RuntimeException {
    public PaymentAlreadyPresentException(String message) {
        super(message);
    }
}

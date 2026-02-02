package com.marketplace.payment_service.exception;

public class InvalidPaymentStateTransitionException extends RuntimeException {
    public InvalidPaymentStateTransitionException(String message) {
        super(message);
    }
}

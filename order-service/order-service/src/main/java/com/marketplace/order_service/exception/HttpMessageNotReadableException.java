package com.marketplace.order_service.exception;

public class HttpMessageNotReadableException extends RuntimeException{
    public HttpMessageNotReadableException(String message){
        super(message);
    }
}

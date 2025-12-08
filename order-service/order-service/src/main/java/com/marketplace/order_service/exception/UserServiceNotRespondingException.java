package com.marketplace.order_service.exception;

public class UserServiceNotRespondingException extends RuntimeException{

    public UserServiceNotRespondingException(String message){
        super(message);
    }


}

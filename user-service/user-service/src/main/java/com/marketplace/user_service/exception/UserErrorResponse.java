package com.marketplace.user_service.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserErrorResponse {
    private String TimeStamp;
    private String message;
    private String path;
    private String error;
    private int status;

    public UserErrorResponse(){}

    public UserErrorResponse(String timeStamp, String message, String path, String error, int status) {
        TimeStamp = timeStamp;
        this.message = message;
        this.path = path;
        this.error = error;
        this.status = status;
    }
}

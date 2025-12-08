package com.marketplace.order_service.exception;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class ErrorResponse {
    private String TimeStamp;
    private String message;
    private String path;
    private String error;
    private int status;

    public ErrorResponse(){}

    public ErrorResponse(String timeStamp, String message, String path, String error, int status) {
        TimeStamp = timeStamp;
        this.message = message;
        this.path = path;
        this.error = error;
        this.status = status;
    }
}

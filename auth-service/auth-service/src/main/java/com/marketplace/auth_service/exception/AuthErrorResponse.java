package com.marketplace.auth_service.exception;



public class AuthErrorResponse {


    private String TimeStamp;
    private String message;
    private String path;
    private String error;
    private int status;

    public AuthErrorResponse(){}

    public AuthErrorResponse(String timeStamp, String message, String path, String error, int status) {
        TimeStamp = timeStamp;
        this.message = message;
        this.path = path;
        this.error = error;
        this.status = status;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

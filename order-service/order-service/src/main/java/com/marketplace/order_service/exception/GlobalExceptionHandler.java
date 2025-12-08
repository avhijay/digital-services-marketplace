package com.marketplace.order_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserServiceNotRespondingException.class)
    public ResponseEntity<ErrorResponse> userServiceNotResponding(UserServiceNotRespondingException e , HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setPath(request.getRequestURI());
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time" +Timestamp.valueOf(LocalDateTime.now()));
        error.setError("User Service not Responding ");
        error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return  new ResponseEntity<>(error,HttpStatus.SERVICE_UNAVAILABLE);
    }



}

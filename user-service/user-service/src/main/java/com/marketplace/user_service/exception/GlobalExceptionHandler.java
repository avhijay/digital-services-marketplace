package com.marketplace.user_service.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);






    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorResponse> userNotFound(UserNotFoundException e , HttpServletRequest request){

        UserErrorResponse error  = new UserErrorResponse();
        error.setError("User not found ");
        error.setMessage(error.getMessage());
        error.setPath(request.getRequestURI());
        error.setTimeStamp(LocalDateTime.now().toString());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);


    }


    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<UserErrorResponse> emailNotFound(UserNotFoundException e , HttpServletRequest request){

        UserErrorResponse error  = new UserErrorResponse();
        error.setError("Email not found ");
        error.setMessage(error.getMessage());
        error.setPath(request.getRequestURI());
        error.setTimeStamp(LocalDateTime.now().toString());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);


    }

}

package com.marketplace.auth_service.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandle {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AuthErrorResponse> methodArgumentInvalid(MethodArgumentNotValidException e , HttpServletRequest request){


        AuthErrorResponse errorResponse = new AuthErrorResponse();


        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp("Time "+ LocalDateTime.now());
        errorResponse.setError("");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);


    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AuthErrorResponse> methodArgumentInvalid(IllegalArgumentException e , HttpServletRequest request){


        AuthErrorResponse errorResponse = new AuthErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp("Time "+ LocalDateTime.now());
        errorResponse.setError("");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);


    }





}

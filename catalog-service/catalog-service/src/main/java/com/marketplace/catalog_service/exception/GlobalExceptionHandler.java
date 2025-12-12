package com.marketplace.catalog_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductErrorResponse> ProductNotFound(ProductNotFoundException e , HttpServletRequest request){

        ProductErrorResponse errorResponse = new ProductErrorResponse();


        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp("Time "+ LocalDateTime.now());
        errorResponse.setError("No Product Found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);



    }

}

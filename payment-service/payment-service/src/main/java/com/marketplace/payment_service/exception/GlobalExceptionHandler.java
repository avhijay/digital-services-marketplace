package com.marketplace.payment_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GlobalExceptionHandler {



        private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        @ExceptionHandler(PaymentAlreadyPresentException .class)
        public ResponseEntity<ErrorResponse> userServiceNotResponding(PaymentAlreadyPresentException e , HttpServletRequest request){
            ErrorResponse error = new ErrorResponse();
            error.setPath(request.getRequestURI());
            error.setMessage(e.getMessage());
            error.setTimeStamp("Time" + Timestamp.valueOf(LocalDateTime.now()));
            error.setError("Payment already exist for the requested order  ");
            error.setStatus(HttpStatus.ALREADY_REPORTED.value());
            return  new ResponseEntity<>(error,HttpStatus.ALREADY_REPORTED);
        }


    @ExceptionHandler(InvalidAmountException.class )
    public ResponseEntity<ErrorResponse> userServiceNotResponding(InvalidAmountException e , HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setPath(request.getRequestURI());
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time" + Timestamp.valueOf(LocalDateTime.now()));
        error.setError("Payment cant be proceed due to invalid amount ");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(PaymentNotFoundException.class )
    public ResponseEntity<ErrorResponse> userServiceNotResponding(PaymentNotFoundException e , HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setPath(request.getRequestURI());
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time" + Timestamp.valueOf(LocalDateTime.now()));
        error.setError("Payment not found  ");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(PaymentFailureException.class )
    public ResponseEntity<ErrorResponse> userServiceNotResponding(PaymentFailureException e , HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setPath(request.getRequestURI());
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time" + Timestamp.valueOf(LocalDateTime.now()));
        error.setError("Payment can not be completed  ");
        error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return  new ResponseEntity<>(error,HttpStatus.SERVICE_UNAVAILABLE);
    }

}

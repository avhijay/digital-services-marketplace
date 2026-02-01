package com.marketplace.payment_service.exception;

import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

// to change error <--> message

@RestControllerAdvice
public class GlobalExceptionHandler {



        private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(InvalidAmountException.class )
    public ResponseEntity<ErrorResponse> InvalidAmount(InvalidAmountException e , HttpServletRequest request){
        ErrorResponse errorResponse= helperMethod(e,"No payment found ",HttpStatus.BAD_REQUEST,request);
        return new ResponseEntity<>( errorResponse, HttpStatus.BAD_REQUEST);

    }



    @ExceptionHandler(PaymentNotFoundException.class )
    public ResponseEntity<ErrorResponse> PaymentNotFound(PaymentNotFoundException e , HttpServletRequest request){
        ErrorResponse errorResponse= helperMethod(e,"No payment found ",HttpStatus.NOT_FOUND,request);
        return new ResponseEntity<>( errorResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(PaymentFailureException.class )
    public ResponseEntity<ErrorResponse> PaymentFailure(PaymentFailureException e , HttpServletRequest request){
        ErrorResponse errorResponse= helperMethod(e,"Payment failed : internal server error",HttpStatus.SERVICE_UNAVAILABLE,request);
        return new ResponseEntity<>( errorResponse,HttpStatus.SERVICE_UNAVAILABLE);
    }


    @ExceptionHandler(DuplicatePaymentException.class )
    public ResponseEntity<ErrorResponse> DuplicatePayment(DuplicatePaymentException e , HttpServletRequest request){
     ErrorResponse errorResponse= helperMethod(e,"Duplicate payment not allowed ",HttpStatus.OK,request);
     return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }


    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<ErrorResponse>optimisticLockException(OptimisticLockException e , HttpServletRequest request){
        ErrorResponse errorResponse= helperMethod(e,"Optimistic lock triggered : fetch data again",HttpStatus.INTERNAL_SERVER_ERROR,request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse>optimisticLockException(OptimisticLockingFailureException e , HttpServletRequest request){
        ErrorResponse errorResponse= helperMethod(e,"Optimistic lock triggered : fetch data again",HttpStatus.CONFLICT,request);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);

    }




    @ExceptionHandler( MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>   methodArgumentNotValidException(   MethodArgumentNotValidException e , HttpServletRequest request){
        ErrorResponse errorResponse= helperMethod(e,"   Method ArgumentNotValid Exception",HttpStatus.BAD_REQUEST,request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }




    private ErrorResponse helperMethod(Exception e , String message, HttpStatus status , HttpServletRequest request){
        ErrorResponse newError = new ErrorResponse();
        newError.setMessage(message);
        newError.setTimeStamp("Time"+Timestamp.valueOf(LocalDateTime.now()));
        newError.setError(e.getMessage());
        newError.setStatus(status.value());
        newError.setPath(request.getRequestURI());
        return newError;
    }


}

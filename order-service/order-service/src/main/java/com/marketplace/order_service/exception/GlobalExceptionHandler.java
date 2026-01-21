package com.marketplace.order_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> orderNotFoundException(OrderNotFoundException e , HttpServletRequest request){


        ErrorResponse error = new ErrorResponse();

        error.setError("No order found with the requested data");
        error.setPath(request.getRequestURI());
        error.setTimeStamp("Time:"+Timestamp.valueOf(LocalDateTime.now()));
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(CatalogServiceNotRespondingException.class)
    public ResponseEntity<ErrorResponse>catalogServiceNotResponding(CatalogServiceNotRespondingException e , HttpServletRequest request){


        ErrorResponse error = new ErrorResponse();
        error.setPath(request.getRequestURI());
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time" +Timestamp.valueOf(LocalDateTime.now()));
        error.setError("Catalog Service not Responding ");
        error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return  new ResponseEntity<>(error,HttpStatus.SERVICE_UNAVAILABLE);
    }







    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e , HttpServletRequest request){

        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getRequestURI());
        error.setError("Invalid format request: VALIDATION FAILED ");
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time"+Timestamp.valueOf(LocalDateTime.now()));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }






@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> dataIntegrityException(DataIntegrityViolationException e , HttpServletRequest request){

    ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setPath(request.getRequestURI());
        error.setError(" Database Constraint voilation ");
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time"+Timestamp.valueOf(LocalDateTime.now()));
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);


}



    @ExceptionHandler(exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception e , HttpServletRequest request){

        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setPath(request.getRequestURI());
        error.setError(" Handled through : handle all exception ");
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time"+Timestamp.valueOf(LocalDateTime.now()));
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);


    }


@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>exception(exception e , HttpServletRequest request) {


    ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setPath(request.getRequestURI());
        error.setError("Something went wrong ");
        error.setMessage(e.getMessage());
        error.setTimeStamp("Time"+Timestamp.valueOf(LocalDateTime.now()));
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
}


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> ProductNotFound(ProductNotFoundException e , HttpServletRequest request){

        ErrorResponse errorResponse = new ErrorResponse();

        log.warn("Product not found with the given credentials={} ",e.getMessage());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp("Time "+ LocalDateTime.now());
        errorResponse.setError("No Product Found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);



    }


}

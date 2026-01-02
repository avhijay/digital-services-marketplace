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
}

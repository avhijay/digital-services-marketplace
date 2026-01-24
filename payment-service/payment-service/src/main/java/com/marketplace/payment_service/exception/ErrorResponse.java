package com.marketplace.payment_service.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {


        private String TimeStamp;
        private String message;
        private String path;
        private String error;
        private int status;


    }



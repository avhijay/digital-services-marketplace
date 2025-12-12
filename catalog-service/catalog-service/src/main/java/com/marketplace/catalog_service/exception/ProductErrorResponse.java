package com.marketplace.catalog_service.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

@Getter
@Setter
public class ProductErrorResponse {

    private String timeStamp;
    private String message;
    private String path;
    private String error;
    private int status;




    public ProductErrorResponse(){}


    public ProductErrorResponse(String timeStamp, String message, String path, String error, int status) {
      this.timeStamp = timeStamp;
        this.message = message;
        this.path = path;
        this.error = error;
        this.status = status;
    }


}

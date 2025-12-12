package com.marketplace.catalog_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {


    private Long productId;
    private String name;
    private String description;
    private Double price;
    private String category;


    public ProductResponse(){}

    public ProductResponse(String category, Double price, String description, String name, Long productId) {
        this.category = category;
        this.price = price;
        this.description = description;
        this.name = name;
        this.productId = productId;
    }
}




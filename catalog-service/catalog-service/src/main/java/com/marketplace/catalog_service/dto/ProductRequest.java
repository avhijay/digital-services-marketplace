package com.marketplace.catalog_service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {


    private String name;
    private String description;
    private Double price;
    private String category;


    public ProductRequest(){}

    public ProductRequest(String name, String description, Double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}

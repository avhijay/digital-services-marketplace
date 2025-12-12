package com.marketplace.catalog_service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProduct {


    private String name;
    private String description;
    private Double price;


    public UpdateProduct(){}


    public UpdateProduct(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}

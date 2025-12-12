package com.marketplace.catalog_service.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {


    private Long productId;
    private String name;
    private String description;
    private Double price;
    private String category;

    public ProductDto(){}

    public ProductDto(String category, Double price, String description, String name, Long product) {
        this.category = category;
        this.price = price;
        this.description = description;
        this.name = name;
        this.productId = product;
    }
}

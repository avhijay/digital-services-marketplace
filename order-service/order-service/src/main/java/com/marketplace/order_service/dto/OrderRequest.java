package com.marketplace.order_service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Long userId;
    private String serviceName;


    public OrderRequest() {
    }

    public OrderRequest(Long userId, String serviceName) {
        this.userId = userId;
        this.serviceName = serviceName;
    }

}

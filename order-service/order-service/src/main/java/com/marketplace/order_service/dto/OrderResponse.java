package com.marketplace.order_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponse {

    private  String userName;
    private String serviceName;
    private String userEmailId;
    private Long userId;
    private Long orderId;



    public OrderResponse(){}


    public OrderResponse(Long orderId, Long userId, String userEmailId, String serviceName, String userName) {
        this.orderId = orderId;
        this.userId = userId;
        this.userEmailId = userEmailId;
        this.serviceName = serviceName;
        this.userName = userName;
    }


}

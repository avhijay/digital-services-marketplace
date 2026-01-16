package com.marketplace.order_service.dto.notification;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class NotificationRequest {


        private Long userId;
        private Long orderId;
        private String message;


        public NotificationRequest(){}

        public NotificationRequest(Long userId, Long orderId, String message) {
            this.userId = userId;
            this.orderId = orderId;
            this.message = message;
        }
    }










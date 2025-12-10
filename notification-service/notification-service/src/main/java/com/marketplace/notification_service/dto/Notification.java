package com.marketplace.notification_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Notification {

    private Long notificationId;
    private Long userId;
    private Long orderId;
    private String message;
    private LocalDateTime createdAt;

    public Notification(){}


    public Notification(LocalDateTime createdAt, Long notificationId, String message, Long userId, Long orderId) {
        this.createdAt = createdAt;
        this.notificationId = notificationId;
        this.message = message;
        this.userId = userId;
        this.orderId = orderId;
    }
}

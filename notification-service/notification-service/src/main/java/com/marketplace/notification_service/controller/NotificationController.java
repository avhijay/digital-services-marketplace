package com.marketplace.notification_service.controller;

import com.marketplace.notification_service.dto.Notification;
import com.marketplace.notification_service.dto.NotificationRequest;
import com.marketplace.notification_service.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    public NotificationController(NotificationService notificationService){
        this.notificationService=notificationService;
    }


    @PostMapping
     ResponseEntity<Notification>create(@RequestBody NotificationRequest request){
        Notification notification = notificationService.createNotification(request);
        return ResponseEntity.ok(notification);
    }
}

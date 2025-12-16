package com.marketplace.notification_service.controller;

import com.marketplace.notification_service.dto.Notification;
import com.marketplace.notification_service.dto.NotificationRequest;
import com.marketplace.notification_service.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;
    public NotificationController(NotificationService notificationService){
        this.notificationService=notificationService;
    }


    @PostMapping
    ResponseEntity<Notification>create(@RequestBody NotificationRequest request){
        log.info("Request create Notification = received ");

        Notification notification = notificationService.createNotification(request);

        log.info("Request : create Notification completed Successfully ");
        return ResponseEntity.ok(notification);
    }
}

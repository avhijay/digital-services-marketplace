package com.marketplace.notification_service.service;


import com.marketplace.notification_service.dto.Notification;
import com.marketplace.notification_service.dto.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private static  final Logger logs = LoggerFactory.getLogger(NotificationService.class);
    private final  AtomicLong idGeneration = new AtomicLong(1);
    private static final Map<Long , List<Notification>> notifications= new ConcurrentHashMap<>();

    public Notification createNotification(NotificationRequest request){
        log.info("Creating Notification for user={} with order={} ",request.getUserId(),request.getOrderId());
        Long notificationId= idGeneration.getAndIncrement();

        Notification notification= new Notification();

        notification.setNotificationId(notificationId);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setOrderId(request.getOrderId() );
        notification.setMessage(request.getMessage());
        notification.setUserId(request.getUserId());


        List<Notification> userNotifications = notifications.getOrDefault(request.getUserId(), new ArrayList<>());



        userNotifications.add(notification);

        notifications.put(request.getUserId(), userNotifications);
log.info("Notification created for user={} with order={} | notification={}",request.getUserId(),request.getOrderId(),notificationId);


        return notification;
    }
}

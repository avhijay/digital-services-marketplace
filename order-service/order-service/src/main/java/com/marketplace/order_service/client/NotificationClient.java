package com.marketplace.order_service.client;


import com.marketplace.order_service.dto.notification.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-SERVICE",contextId ="notificationClient" ,
fallbackFactory = NotificationClientFallbackFactory.class)
public interface NotificationClient {

    @PostMapping("/notifications")

    void createNotification(@RequestBody NotificationRequest request);

}

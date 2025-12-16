package com.marketplace.order_service.client;


import com.marketplace.order_service.dto.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificationClientFallbackFactory implements FallbackFactory<NotificationClient> {
    private static final Logger log = LoggerFactory.getLogger(NotificationClientFallbackFactory.class);


    @Override
    public NotificationClient create(Throwable cause) {
        return new NotificationClient() {
            @Override
            public void createNotification(NotificationRequest request) {


                log.error("Fallback triggered while creating Notification for order={} | cause ={} ", request.getOrderId(),cause.getMessage());


            }
        };
    }
}
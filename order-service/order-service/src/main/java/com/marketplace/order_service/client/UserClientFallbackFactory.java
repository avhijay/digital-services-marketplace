package com.marketplace.order_service.client;

import com.marketplace.order_service.dto.InternalUserDto;
import com.marketplace.order_service.dto.UserDto;
import com.marketplace.order_service.exception.UserServiceNotRespondingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {

    private static final Logger log = LoggerFactory.getLogger(UserClientFallbackFactory.class);


    @Override
    public UserClient create(Throwable cause) {
        return  new UserClient() {
            @Override
            public InternalUserDto getUserById(Long userId) {
                log.error("Fallback Triggered for Catalog Client for productId={} | Reason={}",userId,cause.getMessage());

                throw  new UserServiceNotRespondingException("User-Service not responding");

            }
        };
    }
}

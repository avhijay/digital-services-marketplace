package com.marketplace.order_service.client;

import com.marketplace.order_service.dto.UserDto;
import com.marketplace.order_service.exception.UserServiceNotRespondingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {

    private static final  Logger logs = LoggerFactory.getLogger(UserClientFallbackFactory.class);


    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
            @Override
            public UserDto getUserById(Long userId) {
                logs.error("Unable to fetch user {} , reason :{}",userId,cause.toString());

                throw new UserServiceNotRespondingException("User-service not responding");
            }
        };
    }
}

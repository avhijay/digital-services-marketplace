package com.marketplace.order_service.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class FeignLoggingConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.BASIC;
    }


}

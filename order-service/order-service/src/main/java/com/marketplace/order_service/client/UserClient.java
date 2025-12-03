package com.marketplace.order_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name ="USER-SERVICE")
public interface UserClient {

    @GetMapping("/users/test")

    String getUserTest();

}

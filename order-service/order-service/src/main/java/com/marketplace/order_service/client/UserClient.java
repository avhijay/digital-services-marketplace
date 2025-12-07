package com.marketplace.order_service.client;


import com.marketplace.order_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="USER-SERVICE")
public interface UserClient {

    @GetMapping("/users/{userId}")

    UserDto getUserById(@PathVariable Long userId);




}

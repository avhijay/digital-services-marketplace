package com.marketplace.order_service.controller;


import com.marketplace.order_service.client.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {


private final UserClient userClient;
        public OrderController(UserClient userClient){
    this.userClient=userClient;
        }

    @GetMapping("/test")
    public String test(){
        return "Working - Order-service";
    }
    @GetMapping("/user-test")
    public String callUserService(){
            return "Order service = "+userClient.getUserTest();
    }





}

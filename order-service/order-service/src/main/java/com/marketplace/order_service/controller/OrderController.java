package com.marketplace.order_service.controller;


import com.marketplace.order_service.client.UserClient;
import com.marketplace.order_service.dto.OrderRequest;
import com.marketplace.order_service.dto.OrderResponse;
import com.marketplace.order_service.service.OrderService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {


private final UserClient userClient;
private final OrderService orderService;
        public OrderController(UserClient userClient , OrderService orderService ){
    this.userClient=userClient;
    this.orderService=orderService;
        }

    @GetMapping("/test")
    public String test(){
        return "Working - Order-service";
    }



    @PostMapping
    ResponseEntity<OrderResponse> createUser (@RequestBody OrderRequest orderRequest){
                     OrderResponse order = orderService.createOrder(orderRequest);
                     return ResponseEntity.ok(order);
    }
    @GetMapping("/{id}")
    ResponseEntity <OrderResponse> getUserById(@PathVariable Long id){
            OrderResponse order = orderService.getOrder(id);
            return ResponseEntity.ok(order);
    }







}

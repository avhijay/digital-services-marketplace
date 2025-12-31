package com.marketplace.order_service.controller;


import com.marketplace.order_service.client.UserClient;
import com.marketplace.order_service.dto.OrderRequest;
import com.marketplace.order_service.dto.OrderResponse;
import com.marketplace.order_service.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

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



//    @PostMapping
//    ResponseEntity<OrderResponse> createOrder (@RequestBody OrderRequest orderRequest){
//            log.info("Request: create a Order = received ");
//                     OrderResponse order = orderService.createOrder(orderRequest);
//                     log.info("Request : createOrder  completed Successfully");
//                     return ResponseEntity.ok(order);
//    }



    @GetMapping("/{id}")
    ResponseEntity <OrderResponse> getOrderById(@PathVariable Long id){
            log.info("Request : getOrderById for {} : received ",id);
            OrderResponse order = orderService.getOrder(id);
            log.info("Request : getOrderById for : {}  completed Successfully",id);
            return ResponseEntity.ok(order);
    }







}

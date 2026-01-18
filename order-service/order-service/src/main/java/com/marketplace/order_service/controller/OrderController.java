package com.marketplace.order_service.controller;


import com.marketplace.order_service.client.UserClient;
import com.marketplace.order_service.dto.order.CreateOrderRequest;
import com.marketplace.order_service.dto.order.OrderResponse;
import com.marketplace.order_service.entity.Orders;
import com.marketplace.order_service.service.OrderService;
import com.marketplace.order_service.service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);


private final OrderService orderService;
        public OrderController( OrderService orderService){
    this.orderService=orderService;
        }

    @GetMapping("/test")
    public String test(){
        return "Working - Order-service";
    }


@PostMapping
    public ResponseEntity<OrderResponse>createOrder(@RequestBody CreateOrderRequest createOrderRequest){
            log.info("Request - create order : received");
            OrderResponse response = orderService.createOrder(createOrderRequest);
    URI location = URI.create("/orders"+response.orderId());
       return  ResponseEntity.created(location).body(response);
}


@GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getByOrderId(@PathVariable Long orderId){
            log.info("Request -getOrderById : received");
            OrderResponse response = orderService.getOrderById(orderId);
            return  ResponseEntity.ok(response);

}

@PostMapping("/cancel/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId){
            log.info("Request - cancelOrder : received ");
            orderService.cancelOrder(orderId);
            return ResponseEntity.noContent().build();
}


@PostMapping("/by/user/{userId}")
    public ResponseEntity<List<OrderResponse>>getOrderByUser(@PathVariable Long userId){
            log.info("Request - getOrderByUser : received");
            List<OrderResponse> order = orderService.getOrdersByUser(userId);
            return ResponseEntity.ok(order);

}


}

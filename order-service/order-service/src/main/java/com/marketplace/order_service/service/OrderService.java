package com.marketplace.order_service.service;


import com.marketplace.order_service.client.UserClient;
import com.marketplace.order_service.dto.OrderRequest;
import com.marketplace.order_service.dto.OrderResponse;
import com.marketplace.order_service.dto.UserDto;
import com.marketplace.order_service.exception.UserServiceNotRespondingException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {


private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final UserClient userClient;
    private final AtomicLong idGeneration=new AtomicLong(1);
    private final Map<Long, OrderResponse> orders = new ConcurrentHashMap<>();

    public OrderService(UserClient userClient){
        this.userClient=userClient;
    }






    @CircuitBreaker(name = "userServiceCB",
   fallbackMethod = "createOrderFallback")
    public OrderResponse createOrder(OrderRequest orderRequest){

    UserDto user = userClient.getUserById(orderRequest.getUserId());
    if (user == null) {
        throw new IllegalArgumentException("User not found with userId: " + orderRequest.getUserId());
    }
    Long orderId = idGeneration.getAndIncrement();
    OrderResponse order = new OrderResponse();
    order.setOrderId(orderId);
    order.setServiceName(orderRequest.getServiceName());
    order.setUserEmailId(user.getEmailId());
    order.setUserName(user.getName());
    order.setUserId(user.getUserId());
    orders.put(orderId, order);
    return order;
    }

    public OrderResponse createOrderFallback(   OrderRequest orderRequest ,Throwable ex){

        log.error("User Service did not respond {} | cannot process {}",ex.getMessage(),orderRequest);
        throw new UserServiceNotRespondingException("User Service not Responding ");
    }



    public OrderResponse getOrder(Long id) {

        return orders.get(id);


    }
}

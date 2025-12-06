package com.marketplace.order_service.service;


import com.marketplace.order_service.client.UserClient;
import com.marketplace.order_service.dto.OrderRequest;
import com.marketplace.order_service.dto.OrderResponse;
import com.marketplace.order_service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    private final UserClient userClient;
    private final AtomicLong idGeneration=new AtomicLong(1);
    private final Map<Long, OrderResponse> orders = new ConcurrentHashMap<>();

    public OrderService(UserClient userClient){
        this.userClient=userClient;
    }


    public OrderResponse createOrder(OrderRequest orderRequest){


        UserDto user = userClient.getUserById(orderRequest.getUserId());
        if (user==null){
            throw  new IllegalArgumentException ("User not found with id: "+orderRequest.getUserId());
        }

        Long orderId = idGeneration.getAndIncrement();


        OrderResponse order = new OrderResponse();
        order.setOrderId(orderId);
        order.setServiceName(orderRequest.getServiceName());
        order.setUserEmailId(user.getEmailId());
        order.setUserName(user.getName());
        order.setUserId(user.getUserId());
        orders.put(orderId,order);
        return order;



    }



    public OrderResponse getOrder(Long id) {
        return orders.get(id);


    }
}

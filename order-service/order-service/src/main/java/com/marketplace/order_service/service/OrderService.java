package com.marketplace.order_service.service;

import com.marketplace.order_service.dto.order.CreateOrderRequest;
import com.marketplace.order_service.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {



    OrderResponse createOrder(CreateOrderRequest createOrderRequest);
     OrderResponse getOrderById(Long orderId);
     List<OrderResponse>getOrdersByUser(Long userId);
    void cancelOrder(Long id);




}

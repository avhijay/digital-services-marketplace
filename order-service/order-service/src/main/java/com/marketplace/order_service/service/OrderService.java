package com.marketplace.order_service.service;

import com.marketplace.order_service.dto.CreateOrderRequest;
import com.marketplace.order_service.dto.OrderRequest;
import com.marketplace.order_service.dto.OrderResponse;

import java.util.List;

public interface OrderService {


    OrderResponse placeOrder(CreateOrderRequest request);
    OrderResponse getOrderById(Long id);
    List<OrderResponse>listOrdersByUser(Long id);
    void cancelOrder(Long id);
    OrderResponse updateOrderStatus(OrderRequest request);





}

package com.marketplace.order_service.service;


import com.marketplace.order_service.client.CatalogClient;
import com.marketplace.order_service.client.NotificationClient;
import com.marketplace.order_service.client.UserClient;
import com.marketplace.order_service.dto.CreateOrderRequest;
import com.marketplace.order_service.dto.OrderRequest;
import com.marketplace.order_service.dto.OrderResponse;
import com.marketplace.order_service.entity.Orders;
import com.marketplace.order_service.enums.OrderStatus;
import com.marketplace.order_service.repository.OrderItemRepository;
import com.marketplace.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService {



private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final UserClient userClient;
    private final NotificationClient notificationClient;
    private final OrderRepository orderRepository;
    private final CatalogClient catalogClient;
    private final OrderItemRepository orderItemRepository;


    public OrderServiceImpl(UserClient userClient , NotificationClient notificationClient  , OrderRepository orderRepository , CatalogClient catalogClient , OrderItemRepository orderItemRepository){
        this.userClient=userClient;
        this.notificationClient=notificationClient;
        this.orderRepository=orderRepository;
        this.catalogClient=catalogClient;
        this.orderItemRepository =orderItemRepository;
    }


    @Override
    public OrderResponse placeOrder(CreateOrderRequest request) {





        return null;
    }



    @Override
    public OrderResponse getOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderResponse> listOrdersByUser(Long id) {
        return List.of();
    }

    @Override
    public void cancelOrder(Long id) {

    }

    @Override
    public OrderResponse updateOrderStatus(OrderRequest request) {
        return null;
    }
}

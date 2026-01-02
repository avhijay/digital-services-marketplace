package com.marketplace.order_service.service;


import com.marketplace.order_service.client.NotificationClient;
import com.marketplace.order_service.client.UserClient;
import com.marketplace.order_service.dto.OrderRequest;
import com.marketplace.order_service.dto.OrderResponse;
import com.marketplace.order_service.entity.Orders;
import com.marketplace.order_service.enums.OrderStatus;
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

    public OrderServiceImpl(UserClient userClient , NotificationClient notificationClient  , OrderRepository orderRepository){
        this.userClient=userClient;
        this.notificationClient=notificationClient;
        this.orderRepository=orderRepository;
    }


    @Override
    public OrderResponse placeOrder(OrderRequest request) {
//        log.info("Request received : creating a order ");
//        Orders orders = new Orders();
//        orders.setUserId(request.getUserId());
//        orders.setStatus(OrderStatus.NEW);
//        orders.setTotalAmount(request.ge);




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

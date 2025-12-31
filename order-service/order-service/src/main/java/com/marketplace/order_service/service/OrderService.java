package com.marketplace.order_service.service;


import com.marketplace.order_service.client.NotificationClient;
import com.marketplace.order_service.client.UserClient;
import com.marketplace.order_service.dto.NotificationRequest;
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
    private final NotificationClient notificationClient;
    private final AtomicLong idGeneration=new AtomicLong(1);
    private final Map<Long, OrderResponse> orders = new ConcurrentHashMap<>();

    public OrderService(UserClient userClient , NotificationClient notificationClient){
        this.userClient=userClient;
        this.notificationClient=notificationClient;
    }





//
//    public OrderResponse createOrder(OrderRequest orderRequest){
//
//        log.info("Creating order for userId={}",orderRequest.getUserId());
//        log.debug("Calling userService for  userId={}",orderRequest.getUserId());
//
//        // USER CLIENT
//    UserDto user = userClient.getUserById(orderRequest.getUserId());
//    log.debug("UserService responded successfully for userId={}",orderRequest.getUserId());
//
//
//    if (user == null) {
//        throw new IllegalArgumentException("User not found with userId: " + orderRequest.getUserId());
//    }
//
//    Long orderId = idGeneration.getAndIncrement();
//    OrderResponse order = new OrderResponse();
//    order.setOrderId(orderId);
//    order.setServiceName(orderRequest.getServiceName());
//    order.setUserEmailId(user.getEmailId());
//    order.setUserName(user.getName());
//    order.setUserId(user.getUserId());
//    orders.put(orderId, order);
//
//    //NOTIFICATION CLIENT
//        NotificationRequest request = new NotificationRequest();
//        request.setMessage("Order :"+orderId+" created for user : "+user.getUserId()+"--"+user.getName());
//        request.setUserId(user.getUserId());
//        request.setOrderId(orderId);
//
//        log.info("Generating order for userId={}",orderRequest.getUserId());
//
//        notificationClient.createNotification(request);
//
//log.info("Order created successfully. orderId={}",orderId);
//    return order;
//    }




    public OrderResponse getOrder(Long id) {

        return orders.get(id);


    }
}

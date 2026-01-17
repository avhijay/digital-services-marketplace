package com.marketplace.order_service.service;


import com.marketplace.order_service.client.CatalogClient;
import com.marketplace.order_service.client.NotificationClient;
import com.marketplace.order_service.client.UserClient;
import com.marketplace.order_service.dto.order.CreateOrderRequest;
import com.marketplace.order_service.dto.order.OrderItemResponseData;
import com.marketplace.order_service.dto.order.OrderItemView;
import com.marketplace.order_service.dto.order.OrderResponse;
import com.marketplace.order_service.dto.product.ProductQuantity;
import com.marketplace.order_service.dto.product.ValidateProductsRequest;
import com.marketplace.order_service.dto.product.ValidateProductsResponse;
import com.marketplace.order_service.dto.user.InternalUserDto;
import com.marketplace.order_service.entity.OrderItems;
import com.marketplace.order_service.entity.Orders;
import com.marketplace.order_service.enums.OrderStatus;
import com.marketplace.order_service.exception.OrderNotFoundException;
import com.marketplace.order_service.exception.ProductNotAvailable;
import com.marketplace.order_service.repository.OrderItemRepository;
import com.marketplace.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {


    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final UserClient userClient;
    private final NotificationClient notificationClient;
    private final OrderRepository orderRepository;
    private final CatalogClient catalogClient;
    private final OrderItemRepository orderItemRepository;


    public OrderServiceImpl(UserClient userClient, NotificationClient notificationClient,
                            OrderRepository orderRepository, CatalogClient catalogClient, OrderItemRepository orderItemRepository) {
        this.userClient = userClient;
        this.notificationClient = notificationClient;
        this.orderRepository = orderRepository;
        this.catalogClient = catalogClient;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    @Override
    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        log.info("Request  create order -- order-service : received");
        Long userId = createOrderRequest.userId();

        log.debug("Fetching User={} from user-client", userId);
        // fetching user from client
        InternalUserDto user = userClient.getUserById(userId);
        log.info("User fetched : success");

        //making request for catalog client
        List<ProductQuantity> quantity = createOrderRequest.productQuantities();
        ValidateProductsRequest request = new ValidateProductsRequest(quantity);

// getting productIds stock amount
        Map<Long, Integer> orderQuantity = createOrderRequest.productQuantities().stream()
                .collect(Collectors.toMap(ProductQuantity::productId, ProductQuantity::quantity, Integer::sum));


        // fetching product validation from catalog client
        log.debug("fetching products from catalog-client");
        ValidateProductsResponse response = catalogClient.validateProduct(request);
        log.info("Product fetched: success");
        if (response == null) {
            throw new ProductNotAvailable("Error fetching the products");
        }

// fetching product data and putting  in orderItemResponse
        List<OrderItemResponseData> responseData = response.validateProductResponses().stream()
                .map(item -> new OrderItemResponseData(
                        item.productId(), item.unitPrice(), item.currency(),
                        orderQuantity.get(item.productId()), item.unitPrice()
                        .multiply(BigDecimal.valueOf(orderQuantity.get(item.productId()))))).toList();


        // check same currency


        BigDecimal totalOrderAmount = responseData.stream().map(OrderItemResponseData::lineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        log.info("order creation  : started");
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setOrderNumber(UUID.randomUUID().toString());
        orders.setTotalAmount(totalOrderAmount);
        orders.setStatus(OrderStatus.NEW);
        orders.setCurrency(responseData.stream().map(OrderItemResponseData::currency).toString());
        orderRepository.save(orders);
        log.info("order creation ={} : success ", orders.getId());


        log.info("OrderItem creation : started");
        List<OrderItems> orderItems = responseData.stream().map(orderItemsData ->
                new OrderItems(orders.getId(), orderItemsData.productId(), orderQuantity
                        .get(orderItemsData.productId()), orderItemsData.unitPrice(), orderItemsData.lineTotal())).toList();
        orderItemRepository.saveAll(orderItems);
        log.info("OrderItems for order={} : sucess", orders.getId());


        // updating stock
        log.info("Updating stock : starting ");
        List<ProductQuantity> stockUpdate = createOrderRequest.productQuantities();
        catalogClient.updateStockForOrder(stockUpdate);
        log.info("Stock update : success ");

        log.info("Request create order={} -- order-service : success", orders.getId());
        List<OrderItemView> orderItemView = responseData.stream().map(view ->
                new OrderItemView(view.productId(), view.quantity(), view.unitPrice(), view.lineTotal())).toList();
        return new OrderResponse(orders.getId(), userId, OrderStatus.NEW, totalOrderAmount, orders.getCurrency(), orderItemView);


    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("No order available with id: " + orderId));
        return mapFromEntity(order);

    }

    @Override
    public List<OrderResponse> getOrdersByUser(Long userId) {
        List<Orders> ordersByUser = orderRepository.findByUserId(userId);


        return null;
    }

    @Override
    public void cancelOrder(Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("No order available with id: " + id));
        orders.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(orders);

    }

    private OrderResponse mapFromEntity(Orders order) {
        List<OrderItems> orderItems = orderItemRepository.findByOrderId(order.getId());
        List<OrderItemView> newOrdersItems = orderItems.stream().map(items -> new OrderItemView(items.getProductId(), items.getQuantity(), items.getUnitPrice(), items.getLineTotal())).toList();
        return new OrderResponse(order.getId(), order.getUserId(), order.getStatus(), order.getTotalAmount(), order.getCurrency(), newOrdersItems);
    }
}


//    private List< OrderResponse> mapOrdersFromEntity( List<Orders> order){
//
//
//
////        List<OrderItems> orderItems = orderItemRepository.findByOrderIdIn()
////        List<OrderItemView> newOrdersItems = orderItems.stream().map( items-> new OrderItemView(items.getProductId(),
////                items.getQuantity(), items.getUnitPrice(),items.getLineTotal())).toList();
//
//
//
//
//
//
//}
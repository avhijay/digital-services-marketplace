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
import com.marketplace.order_service.exception.ProductNotAvailable;
import com.marketplace.order_service.repository.OrderItemRepository;
import com.marketplace.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


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


    public OrderServiceImpl(UserClient userClient , NotificationClient notificationClient  , OrderRepository orderRepository , CatalogClient catalogClient , OrderItemRepository orderItemRepository){
        this.userClient=userClient;
        this.notificationClient=notificationClient;
        this.orderRepository=orderRepository;
        this.catalogClient=catalogClient;
        this.orderItemRepository =orderItemRepository;
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        log.info("Request  create order -- order-service : received");
        Long userId = createOrderRequest.userId();

        log.debug("Fetching User={} from user-client",userId);
        // fetching user from client
        InternalUserDto user = userClient.getUserById(userId);
        log.info("User fetched : success");

        //making request for catalog client
        List<ProductQuantity> quantity = createOrderRequest.productQuantities();
        ValidateProductsRequest request = new ValidateProductsRequest(quantity);

// getting productIds stock amount
        Map<Long , Integer> orderQuantity = createOrderRequest.productQuantities().stream().collect(Collectors.toMap(ProductQuantity::productId,ProductQuantity::quantity ,Integer::sum));


        // fetching product validation from catalog client
        log.debug("fetching products from catalog-client");
        ValidateProductsResponse response = catalogClient.validateProduct(request);
        log.info("Product fetched: success");

// fetching product data and putting  in orderItemResponse
List<OrderItemResponseData> responseData =  response.validateProductResponses().stream()
        .map(item-> new OrderItemResponseData(
                item.productId(),item.unitPrice(),item.currency(),
                item.availableStock(),item.unitPrice()
                .multiply(BigDecimal.valueOf(orderQuantity.get(item.productId()))))).toList();


BigDecimal totalOrderAmount  = responseData.stream().map(OrderItemResponseData::lineTotal)
        .reduce(BigDecimal.ZERO,BigDecimal::add);

log.info("order creation  : started");
Orders orders = new Orders();
orders.setUserId(userId);
orders.setOrderNumber(UUID.randomUUID().toString());
orders.setTotalAmount(totalOrderAmount);
orders.setStatus(OrderStatus.NEW);
orders.setCurrency(responseData.stream().map(OrderItemResponseData::currency).toString());
orderRepository.save(orders);
log.info("order creation ={} : success ",orders.getId());


log.info("OrderItem creation : started");
       List <OrderItems> orderItems = responseData.stream().map( orderItemsData-> new OrderItems(orders.getId(),orderItemsData.productId(),orderQuantity
                .get(orderItemsData.productId()),orderItemsData.unitPrice(),orderItemsData.lineTotal())).toList();
       orderItemRepository.saveAll(orderItems);
       log.info("OrderItems for order={} : sucess",orders.getId());


       // updating stock
        log.info("Updating stock : starting ");
        List<ProductQuantity>stockUpdate = createOrderRequest.productQuantities();
        catalogClient.updateStockForOrder(stockUpdate);
        log.info("Stock update : success ");

        log.info("Request create order={} -- order-service : success",orders.getId());
      List<  OrderItemView >orderItemView = responseData.stream().map(view-> new OrderItemView(view.productId(), view.quantity(), view.unitPrice(),view.lineTotal())).toList();
        return new OrderResponse(orders.getId(), userId,OrderStatus.NEW,totalOrderAmount,orders.getCurrency(),orderItemView);




    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderResponse> getOrdersByUser(Long userId) {
        return List.of();
    }

    @Override
    public void cancelOrder(Long id) {

    }
}

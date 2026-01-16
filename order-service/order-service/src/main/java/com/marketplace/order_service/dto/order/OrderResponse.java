package com.marketplace.order_service.dto.order;

import com.marketplace.order_service.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(

Long orderId,
Long userId,
OrderStatus status,
BigDecimal totalAmount,
String currency,
List<OrderItemView>orderItemViews

) {



}

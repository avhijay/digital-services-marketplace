package com.marketplace.order_service.repository;

import com.marketplace.order_service.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItems , Long> {

    List<OrderItems> findByOrderId(Long orderId);

    List<OrderItems> findByOrderIdIn(List<Long>orderIds);

    long countByOrderId(Long orderId);

    List<OrderItems> findByProductId(Long productId);
}

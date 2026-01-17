package com.marketplace.order_service.repository;

import com.marketplace.order_service.entity.Orders;
import com.marketplace.order_service.enums.OrderStatus;
import com.marketplace.order_service.service.OrderIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    List<Orders> findByUserId(Long userId);

    List<Orders> findByStatus(String status);

    List<Orders> findTop10ByUserIdOrderByCreatedAtDesc(Long userId);

    boolean existsByUserId(Long userId);

    long countByStatus(OrderStatus status);

    List<OrderIdProjection>findOrderIdsByUserId(Long userId);

    List<Orders> findByUserIdAndStatus(Long userId , OrderStatus status);

    List<Orders> findByCreatedAtBetween(LocalDateTime start , LocalDateTime end);

}

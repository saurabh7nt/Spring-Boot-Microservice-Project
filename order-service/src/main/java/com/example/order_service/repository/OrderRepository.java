package com.example.order_service.repository;

import com.example.order_service.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find orders by user ID with pagination
    Page<Order> findByUserId(Long userId, Pageable pageable);

    // Find orders by status with pagination
    Page<Order> findByStatus(String status, Pageable pageable);

    // Find orders by user ID and status
    List<Order> findByUserIdAndStatus(Long userId, String status);

    // Find orders by user ID within date range
    @Query("SELECT o FROM Order o WHERE o.userId = :userId AND o.orderDate BETWEEN :startDate AND :endDate ORDER BY o.orderDate DESC")
    List<Order> findByUserIdAndDateRange(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    // Find all orders by user ID ordered by date
    List<Order> findByUserIdOrderByOrderDateDesc(Long userId);
}



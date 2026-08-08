package com.navya.platform.order;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    long countByStatus(OrderStatus status);
}

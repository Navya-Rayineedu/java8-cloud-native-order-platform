package com.navya.platform.order;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderService(OrderRepository repository, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    @CacheEvict(value = "orders", key = "#result.id")
    public Order create(CreateOrderRequest request) {
        Order order = repository.save(new Order(
                request.getCustomerId(), request.getProductId(), request.getQuantity(), request.getUnitPrice()));

        BigDecimal total = request.getUnitPrice().multiply(BigDecimal.valueOf(request.getQuantity()));
        kafkaTemplate.send("orders.created", order.getId().toString(),
                new OrderEvent(order.getId(), order.getCustomerId(), total));
        return order;
    }

    @Cacheable(value = "orders", key = "#id")
    public Order get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
    }

    public Page<Order> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public int processBatch() {
        List<Order> orders = repository.findAll(PageRequest.of(0, 500)).getContent();        orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.CREATED)
                .forEach(o -> o.setStatus(OrderStatus.PROCESSING));
        repository.saveAll(orders);
        return (int) orders.stream().filter(o -> o.getStatus() == OrderStatus.PROCESSING).count();
    }
}

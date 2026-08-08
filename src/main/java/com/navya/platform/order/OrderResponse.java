package com.navya.platform.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class OrderResponse {
    private UUID id; private String customerId; private String productId; private int quantity;
    private BigDecimal unitPrice; private OrderStatus status; private Instant createdAt;
    public OrderResponse(UUID id, String customerId, String productId, int quantity, BigDecimal unitPrice, OrderStatus status, Instant createdAt) {
        this.id=id; this.customerId=customerId; this.productId=productId; this.quantity=quantity;
        this.unitPrice=unitPrice; this.status=status; this.createdAt=createdAt;
    }
    public static OrderResponse from(Order o) {
        return new OrderResponse(o.getId(),o.getCustomerId(),o.getProductId(),o.getQuantity(),o.getUnitPrice(),o.getStatus(),o.getCreatedAt());
    }
    public UUID getId(){return id;} public String getCustomerId(){return customerId;} public String getProductId(){return productId;}
    public int getQuantity(){return quantity;} public BigDecimal getUnitPrice(){return unitPrice;} public OrderStatus getStatus(){return status;}
    public Instant getCreatedAt(){return createdAt;}
}

package com.navya.platform.order;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderEvent {
    private UUID orderId; private String customerId; private BigDecimal totalAmount;
    public OrderEvent() {}
    public OrderEvent(UUID orderId, String customerId, BigDecimal totalAmount) {
        this.orderId=orderId; this.customerId=customerId; this.totalAmount=totalAmount;
    }
    public UUID getOrderId(){return orderId;} public String getCustomerId(){return customerId;} public BigDecimal getTotalAmount(){return totalAmount;}
    public void setOrderId(UUID v){orderId=v;} public void setCustomerId(String v){customerId=v;} public void setTotalAmount(BigDecimal v){totalAmount=v;}
}

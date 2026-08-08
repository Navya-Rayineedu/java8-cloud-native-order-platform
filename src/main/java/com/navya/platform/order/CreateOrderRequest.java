package com.navya.platform.order;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class CreateOrderRequest {
    @NotBlank private String customerId;
    @NotBlank private String productId;
    @Min(1) private int quantity;
    @NotNull @DecimalMin("0.01") private BigDecimal unitPrice;

    public CreateOrderRequest() {}
    public String getCustomerId() { return customerId; }
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setCustomerId(String v) { customerId = v; }
    public void setProductId(String v) { productId = v; }
    public void setQuantity(int v) { quantity = v; }
    public void setUnitPrice(BigDecimal v) { unitPrice = v; }
}

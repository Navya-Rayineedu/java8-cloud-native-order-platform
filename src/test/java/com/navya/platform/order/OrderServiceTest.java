package com.navya.platform.order;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    private CreateOrderRequest request(String c, String p, int q, BigDecimal price) {
        CreateOrderRequest r = new CreateOrderRequest();
        r.setCustomerId(c); r.setProductId(p); r.setQuantity(q); r.setUnitPrice(price);
        return r;
    }

    @Test
    void createsOrderAndPublishesEvent() {
        OrderRepository repo = mock(OrderRepository.class);
        KafkaTemplate<String, OrderEvent> kafka = mock(KafkaTemplate.class);

        when(repo.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        OrderService service = new OrderService(repo, kafka);
        Order order = service.create(request("C1", "P1", 2, new BigDecimal("10.00")));

        assertEquals("C1", order.getCustomerId());
        assertEquals(OrderStatus.CREATED, order.getStatus());

        ArgumentCaptor<OrderEvent> captor = ArgumentCaptor.forClass(OrderEvent.class);
        verify(kafka).send(eq("orders.created"), eq(order.getId().toString()), captor.capture());
        assertEquals(new BigDecimal("20.00"), captor.getValue().getTotalAmount());
    }
}

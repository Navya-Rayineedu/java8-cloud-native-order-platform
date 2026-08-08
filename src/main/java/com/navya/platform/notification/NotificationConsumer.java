package com.navya.platform.notification;

import com.navya.platform.order.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
    @KafkaListener(topics = "orders.created", groupId = "notification-service")
    public void consume(OrderEvent event) {
        // Replace with email/SMS/push integration in a real service.
        System.out.printf("Notification: order=%s customer=%s total=%s%n",
                event.getOrderId(), event.getCustomerId(), event.getTotalAmount());
    }
}
